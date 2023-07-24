# This script creates variables out of all states' resources
# for first-time setup of EOANB
# This creates a load of state scopes with variable assignments which you can put into an event's effects
from glob import *
import re

states_path = "history/states/*.txt"

all_state_files = iglob(states_path)

resources_file = "resources_master.txt"

def lookat(file):
    open(file, "rt")

def add_values(state_id, resources):
    f = open(resources_file, "a+")
    f.write(
        "\n" +
        state_id + "= {"
        )
    for resource in resources:
        f.write(
            "set_variable = { r_" + resource + "} \n"
        )
    f.write(
        "} \n"
        )
    f.close

for state_filepath in all_state_files:
    # Get only numbers from the filename, which should be the ID
    state_id_location = re.findall(r"\d+", state_filepath)
    state_id = state_id_location[0]
    state_info = open(state_filepath, encoding="utf8")
    for resources in re.findall("resources = {(.*?)}", state_info.read(), re.S):
        # Remove all comments
        resources = ''.join(resources.split("#")[0])
        # Make lists of the resources in states
        resources_list = resources.strip().splitlines()
    add_values(state_id, resources_list)

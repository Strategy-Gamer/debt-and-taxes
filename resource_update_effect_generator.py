# Use this script to quickly generate a set of scripted effects
# These are used to update variables in states when resources change in them

resources = [
    "wood",
    "coal",
    "cotton",
    "fabric",
    "iron",
    "steel",
    "rubber",
    "oil",
    "plastics",
    "aluminium",
    "tungsten",
    "chromium",
    "titanium",
    "silicates",
    "ceramics",
    "machinery",
    "rare_earth_metals",
    "eletronics",
    "portable_power"
    ]

for resource in resources:
    print(
        "update_"+resource+" = {\n"
        "   add_to_variable = { r_"+resource+" = r_amt }\n"
        "   if = {\n"
        "      limit = {\n"
        "            # Check if it exists, and if not then create it\n"
        "            NOT = {has_variable = r_"+resource+"}\n"
        "      }\n"
        "      set_variable = { r_"+resource+" = r_amt }\n"
        "   }\n"
        "   clear_variable = r_amt\n"
        "}\n"
    )

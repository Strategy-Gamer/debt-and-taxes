
#
pops = [
	"Pops",
	"Slaves",
	"Farmers",
	"Laborers",
	"Craftsmen",
	"Clerks",
	"Soldiers",
	"Professionals",
	"Businessmen",
	"Bureaucrats",
	"Capitalists",
	"Retirees"
]

resources = [
	"Oil",
	"Aluminium",
	"Rubber",
	"Tungsten",
	"Iron",
	"Chromium",
	"Copper",
	"Precious Metals",
	"Coal",
	"Lumber",
	"Fiber",
	"Foodstuffs",
	"Electricity",
	"Steel",
	"Electric Components",
	"Construction Materials",
	"Silicate Materials",
	"Chemicals",
	"Fabric",
	"Machinery",
	"Plastics",
	"Advanced Engines",
	"Hydrocarbons",
	"Automobiles",
	"Aeroplanes",
	"Computers",
	"Electronics",
	"Groceries",
	"Pharmaceuticals",
	"Clothing",
	"Consumer Goods",
	"Wooden Convoys",
	"Convoys",
	"Small Arms",
	"Artillery",
	"Tanks",
	"Explosives",
	"Rockets",
	"Construction",
	"Maintenance",
	"Healthcare",
	"Basic Education",
	"Higher Education",
	"Transport",
	"Shipping",
	"Finance",
	"Housing",
	"Entertainment"
]

industries = [
	["Oil Rig", "Oil"],
	["Aluminium Mine", "Aluminium"],
	["Rubber Lodge", "Rubber"],
	["Synthetic Rubber Plant", "Rubber"],
	["Tungsten Mine", "Tungsten"],
	["Iron Mine", "Iron"],
	["Chromium Mine", "Chromium"],
	["Copper Mine", "Copper"],
	["Precious Metals Mine", "Precious Metals"],
	["Coal Mine", "Coal"],
	["Lumber Lodge", "Lumber"],
	["Cotton Plantation", "Fiber"],
	["Sheep Ranch", "Fiber"],
	["Grain Farm", "Foodstuffs"],
	["Livestock Ranch", "Foodstuffs"],
	["Fishing Wharf", "Foodstuffs"],
	["Coal Power Plant", "Electricity"],
	["Oil Power Plant", "Electricity"],
	["Nuclear Power Plant", "Electricity"],
	["Wind Farm", "Electricity"],
	["Solar Farm", "Electricity"],
	["Hydroelectric Dam", "Electricity"],
	["Steel Factory", "Steel"],
	["Electric Components Factory", "Electric Components"],
	["Construction Materials Factory", "Construction Materials"],
	["Silicate Materials Factory", "Silicate Materials"],
	["Chemical Plant", "Chemicals"],
	["Textile Mill", "Fabric"],
	["Machinery Factory", "Machinery"],
	["Plastics Factory", "Plastics"],
	["Advanced Engines Factory", "Advanced Engines"],
	["Oil Refinery", "Hydrocarbons"],
	["Synthetic Oil Plant", "Hydrocarbons"],
	["Automobile Factory", "Automobiles"],
	["Aeroplane Factory", "Aeroplanes"],
	["Computer Factory", "Computers"],
	["Electronics Factory", "Electronics"],
	["Food Processing Plant", "Groceries"],
	["Pharmaceuticals Factory", "Pharmaceuticals"],
	["Clothing Factory", "Clothing"],
	["Consumer Goods Factory", "Consumer Goods"],
	["Wooden Convoys Shipyard", "Wooden Convoys"],
	["Convoys Shipyard", "Convoys"],
	["Small Arms Factory", "Small Arms"],
	["Artillery Factory", "Artillery"],
	["Tank Factory", "Tanks"],
	["Explosive Factory", "Explosives"],
	["Rocket Factory", "Rockets"],
	["Construction Yard", "Construction"],
	["Maintenance Facility", "Maintenance"],
	["Hospital", "Healthcare"],
	["School", "Basic Education"],
	["University", "Higher Education"],
	["Transport Hub", "Transport"],
	["Shipping Hub", "Shipping"],
	["Finance Hub", "Finance"],
	["Housing Office", "Housing"],
	["Entertainment Complex", "Entertainment"]
]


#Scripted Effect Names
names = [
	"GetResourceTextIcon",
	"GetResourceText",
	"GetResourceCode",
	"GetResourceDesc"
]
names2 = [
	"GetIndustryText",
	"GetIndustryCode"
]

def getResourceCodeName(resource_type):
	return getCodeName(resources[resource_type])
	
def getResourceIndex(resource_type): #Parameter is a String, Return is integer
	i = 0
	while i < len(resources):
		if resource_type == resources[i]:
			return i
		i += 1
	return -1
	
def getCodeName(string):
	return string.lower().replace(" ", "_")

#Reminder to change Oil to Crude Oil in GetResourceText
#Reminder to change Aluminium to Aluminum in GetResourceText

########################################################################

loc_type = int(input())


if loc_type == 0:
	print("no")

elif loc_type == 6:
	#Pops
	type1_list = [	#Should be normal like the resource[] list
		"Religion",
		"Culture",
		"Ideology"
	]
	type2_list = [ #Like above but add a space at the beginning. If you want none, then just ensure it's a string with nothing in it ("").
		" Amount",
		" Percentage"
	]
	#The two above get merged together like this: type1_list + type2_list, so "Employees" + "Farmers Amount" turns into "Employees Farmers Amount"

	print("")
	type1 = 0
	while type1 < len(type1_list):
		type2 = 0
		while type2 < len(type2_list):
			type_name = type1_list[type1] + type2_list[type2]

			print("#GetPops" + type_name.replace(" ", ""))
			print("\tdefined_text = {")
			print("\t\tname = GetPops" + type_name.replace(" ", ""))
			pop = 0
			while pop < len(pops):
				print("\t\ttext = {")
				print("\t\t\ttrigger = {")
				print("\t\t\t\tcheck_variable = { selected_pop_type = " + str(pop) + " }")
				#print("\t\t\t\tstate = " + str(pop + 1))
				print("\t\t\t}")
				print("\t\t\tlocalization_key = " + getCodeName(pops[pop]) + "_" + getCodeName(type_name))
				print("\t\t}")
				pop += 1
			print("\t}")
			type2 += 1
		type1 += 1

	#Seperated by 2 spaces and two lines of #'s
	print("#######################################################################################################################################")
	print("#######################################################################################################################################")
	print()
	print()

	type1 = 0
	while type1 < len(type1_list):
		type2 = 0
		while type2 < len(type2_list):
			type_name = type1_list[type1] + type2_list[type2]

			print("  pops_" + getCodeName(type_name) + "_loc:0 \"[GetPops" + type1_list[type1].replace(" ", "") + type2_list[type2].replace(" ", "") + "]\"")
			pop = 0
			while pop < len(pops):
				if type1_list[type1].find("Percentage") > 0 or type2_list[type2].find("Percentage") > 0:
					endStr = "1%"
				else:
					endStr = "3"
				print("   " + getCodeName(pops[pop]) + "_" + getCodeName(type_name) + ":0 \"[?selected_state:" + getCodeName(pops[pop]) + "_" + getCodeName(type_name) + "|" + endStr +"]\"")

				pop += 1
			type2 += 1
		type1 += 1

	#Ended with a space and a #
	print("#")
	print()


elif loc_type == 7:
	#Pops Needs
	#This is specifically for pops' needs list

	print("#GetPopsNeeds(Num)")
	index = 0
	while index < 30:
		type_name = "Needs " + str(index)

		print("\t#GetPops" + type_name.replace(" ", ""))
		print("\t\tdefined_text = {")
		print("\t\t\tname = GetPops" + type_name.replace(" ", ""))
		print("\t\t\ttext = {")
		print("\t\t\t\ttrigger = {")
		print("\t\t\t\t\tcheck_variable = { selected_state:pops_needs_amount^" + str(index) + " > 0 }")
		print("\t\t\t\t}")
		print("\t\t\t\tlocalization_key = pops_" + getCodeName(type_name))
		print("\t\t\t}")
		print("\t\t}")
		index += 1

	#Seperated by 2 spaces and two lines of #'s
	print("#######################################################################################################################################")
	print("#######################################################################################################################################")
	print()
	print()

	index = 0
	print("  #GetPopsNeeds(Num)")
	while index < 30:
		type_name = "Needs " + str(index)

		print("   pops_" + getCodeName(type_name) + ":0 \"\\n[?selected_state:pops_needs_type^" + str(index) + ".GetPopsNeedsTypeUppercase] - [?selected_state:pops_needs_resource^" + str(index) + ".GetResourceTextIcon] [?selected_state:pops_needs_resource^" + str(index) + ".GetResourceText] §Y[?selected_state:pops_needs_fulfilled^" + str(index) + "|3]§! / §Y[?selected_state:pops_needs_amount^" + str(index) + "|3]§! (§Y[?selected_state:pops_needs_fulfilled_percentage^" + str(index) + "|1%]§!)\"")

		index += 1

	#Ended with a space and a #
	print("#")
	print()
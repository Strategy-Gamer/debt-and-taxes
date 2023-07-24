
#
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

def createLocEntry(name, entry):
	print(name + ":0 \"" + entry + "\"")

#Reminder to change Oil to Crude Oil in GetResourceText
#Reminder to change Aluminium to Aluminum in GetResourceText

########################################################################

loc_type = int(input())

if loc_type == 0:
	#Do not change
	terrain_list = [
		"Flat",
		"Hilly",
		"Mountainous",
		"Dunes",
		"Swamp",
		"Forest"
	]
	modifier_list = [
		"Effects",
		"Building Slots",
		"Build Cost Modifier",
		"Building Maintenance Modifier",
		"Transport Cost Modifier",
		"Grain Farm Output Modifier"
	]

	print("")
	i = 0
	while i < len(terrain_list):
		terrain = terrain_list[i]
		j = 0
		while j < len(modifier_list):
			modifier = modifier_list[j]

			type_name = terrain_list[terrain] + " Terrain " + modifier_list[modifier]

			print("\t#Get" + type_name.replace(" ", ""))
			print("\t\tdefined_text = {")
			print("\t\t\tname = Get" + terrain_list[terrain].replace(" ", "") + "Terrain" + modifier_list[modifier].replace(" ", ""))
			print("\t\t\ttext = {")

			begin = ""
			if modifier > 0:
				begin = "max_"
				print("\t\t\t\ttrigger = {")
				if terrain == 5 and modifier == 1:
					print("\t\t\t\t\tNOT = { check_variable = { global.forest_building_slots_modifier = 0 } }")
				else:
					print("\t\t\t\t\tNOT = { check_variable = { global." + getCodeName(terrain_list[terrain]) + "_" + getCodeName(modifier_list[modifier]) + " = 0 } }")
				print("\t\t\t\t}")
			print("\t\t\t\tlocalization_key = " + begin + getCodeName(type_name))
			print("\t\t\t}")
			print("\t\t}")
			j += 1
		if terrain == 5:
			print("\t#GetForestTerrainLumberLodgeOutputModifier")
			print("\t\tdefined_text = {")
			print("\t\t\tname = GetForestTerrainLumberLodgeOutputModifier")
			print("\t\t\ttext = {")
			print("\t\t\t\ttrigger = {")
			print("\t\t\t\t\tNOT = { check_variable = { global.forest_lumber_lodge_output_modifier = 0 } }")
			print("\t\t\t\t}")
			print("\t\t\t\tlocalization_key = max_forest_terrain_lumber_lodge_output_modifier")
			print("\t\t\t}")
			print("\t\t}")
		i += 1

	#Seperated by 2 spaces and two lines of #'s
	print("#######################################################################################################################################")
	print("#######################################################################################################################################")
	print()
	print()

	terrain = 0
	while terrain < len(terrain_list):
		modifier = 0
		while modifier < len(modifier_list):
			type_name = terrain_list[terrain] + " Terrain " + modifier_list[modifier]
			if terrain == 5 and modifier == 1:
				type_name += " Modifier"

			loc_end = "" #TODO - finish this bit here
			if modifier == 1:
				loc_end += "|0"
			if modifier != 1 or terrain == 5:
				loc_end += "|1%"
			else:
				loc_end += "|3"
			if modifier == 0:
				modifier_alt = 1
				loc_modifiers = ""
				while modifier_alt < len(modifier_list):
					loc_modifiers += "[Get" + terrain_list[terrain].replace(" ", "") + "Terrain" + modifier_list[modifier_alt].replace(" ", "") + "]"
					modifier_alt += 1
				createLocEntry("  " + getCodeName(type_name), terrain_list[terrain] + " Terrain:\\n" + loc_modifiers)
			else:
				modifier_alt = 1
				createLocEntry("  max_" + getCodeName(type_name) + "_positive", "+[?global." + getCodeName(terrain_list[terrain]) + "_" + getCodeName(modifier_list[modifier]) + loc_end + "] " + modifier_list[modifier] + "\\n")
				createLocEntry("  max_" + getCodeName(type_name) + "_negative", "[?global." + getCodeName(terrain_list[terrain]) + "_" + getCodeName(modifier_list[modifier]) + loc_end + "] " + modifier_list[modifier] + "\\n")

			modifier += 1
		terrain += 1

	#Ended with a space and a #
	print("#")
	print()



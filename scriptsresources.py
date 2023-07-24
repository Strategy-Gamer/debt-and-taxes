
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

#Reminder to change Oil to Crude Oil in GetResourceText
#Reminder to change Aluminium to Aluminum in GetResourceText

########################################################################

loc_type = int(input())

if loc_type == 0:
	#General Resource Scripted Effects
	num = 0
	while num < len(names):
		print("")
		print("#" + names[num])
		print("\tdefined_text = {")
		print("\t\tname = " + names[num])
		resource = 0
		while resource < len(resources):
			print("\t\ttext = {")
			print("\t\t\ttrigger = {")
			print("\t\t\t\tstate = " + str(resource + 2))
			print("\t\t\t}")
			if num == 0:
				print("\t\t\tlocalization_key = \"[Get" + resources[resource].replace(" ", "") + "Icon]\"")
			if num == 1:
				print("\t\t\tlocalization_key = \"" + resources[resource] + "\"")
			if num == 2:
				print("\t\t\tlocalization_key = \"" + getResourceCodeName(resource) + "\"")
			if num == 3:
				print("\t\t\tlocalization_key = \"" + resources[resource].lower().replace(" ", "_") + "_desc\"")
			print("\t\t}")
			resource += 1
		print("\t}")
		if num == 0:
			print("#Text Icons")
			resource = 0
			while resource < len(resources):
				print("\tdefined_text = {")
				print("\t\tname = " + "Get" + resources[resource].replace(" ", "") + "Icon")
				print("\t\ttext = {")
				print("\t\t\tlocalization_key = " + resources[resource].upper().replace(" ", "_") + "_TEXTICON")
				print("\t\t}")
				print("\t}")
				resource += 1
		num += 1

elif loc_type == 1:

	#Texticon Localization

	resource = 0
	while resource < len(resources):
		print("  " + getCodeName(resources[resource]).upper() + "_TEXTICON" + ":0 \"£" + getCodeName(resources[resource]) + "_texticon\"")
		resource += 1

	print("##########################################################################")
	
	#Texticon GFX entries

	resource = 0
	while resource < len(resources):
		print("\tspriteType = {")
		print("\t\tname = \"GFX_" + getCodeName(resources[resource]) + "_texticon\" #Don't use directly as texticons, use [variable.GetResourceTextIcon]")
		print("\t\ttexturefile = \"gfx/texticons/" + getCodeName(resources[resource]) + "_texticon.dds\"")
		print("\t\tlegacy_lazy_load = no")
		print("\t}")

		resource += 1

elif loc_type == 11:
	#Specific Resource Scripted Effects
	type = "Profit" #Should be normal like the resource[] list
	print("#GetResources" + type.replace(" ", ""))
	print("\tdefined_text = {")
	print("\t\tname = GetResources" + type.replace(" ", ""))
	resource = 0
	while resource < len(resources):
		print("\t\ttext = {")
		print("\t\t\ttrigger = {")
		print("\t\t\t\tOR = {")
		print("\t\t\t\t\tAND = {")
		print("\t\t\t\t\t\tstate = 1")
		print("\t\t\t\t\t\tcheck_variable = { global.natural_resources_array^i = " + str(resource + 2) + " }")
		print("\t\t\t\t\t}")
		print("\t\t\t\t\tAND = {")
		print("\t\t\t\t\t\tstate = 2")
		print("\t\t\t\t\t\tcheck_variable = { global.produced_resources_array^i = " + str(resource + 2) + " }")
		print("\t\t\t\t\t}")
		print("\t\t\t\t\tAND = {")
		print("\t\t\t\t\t\tstate = 3")
		print("\t\t\t\t\t\tcheck_variable = { global.service_resources_array^i = " + str(resource + 2) + " }")
		print("\t\t\t\t\t}")
		print("\t\t\t\t}")
		print("\t\t\t}")
		print("\t\t\tlocalization_key = " + getResourceCodeName(resource) + "_" + getCodeName(type))
		print("\t\t}")
		resource += 1
	print("\t}")
		
elif loc_type == 12:
	#Specific Resource Localization Entries
	type = "Profit" #Should be normal like the resource[] list
	print(" #")
	print("  natural_resources_" + getCodeName(type) + "_loc:0 \"[1.GetResources" + type.replace(" ", "") + "]\"")
	print("  produced_resources_" + getCodeName(type) + "_loc:0 \"[2.GetResources" + type.replace(" ", "") + "]\"")
	print("  service_resources_" + getCodeName(type) + "_loc:0 \"[3.GetResources" + type.replace(" ", "") + "]\"")
	resource = 0
	while resource < len(resources):
		print("   " + getResourceCodeName(resource) + "_" + getCodeName(type) + ":0 \"[?selected_state:" + getResourceCodeName(resource) + "_" + getCodeName(type) + "|3]\"")

		resource += 1
	
	
	
	



import os
import os.path
os.chdir("history\states")

### Fetch all files from current working directory defined at line 3 into a list
states = [f for f in os.listdir() if os.path.isfile(os.path.join(f))]
"""
Slaves - 0.0%
Farmers - 24.9% 
Laborers - 12.4%
Craftsmen - 16.4%
Clerks - 15.3%
Soldiers - 0.5%
Professionals - 22.1%
Businessmen - 7.2%
Bureaucrats - 1.0%
Capitalists - 0.2%
"""

pop_type = [0.0, 0.249, 0.124, 0.164, 0.153, 0.005, 0.221, 0.072, 0.01, 0.002] #USA Pop percentage, CONSTANTS!!!

"""
Pop_Type Indexes
Slaves - 0, 
Farmers - 1, 
Laborers - 2, 
Craftsmen - 3, 
Clerks - 4, 
Professionals - 6, 
Businessmen - 7
"""
for x in range(len(states)):

    file = states[x] #For some reason you just cant trust the index in a for loop in Python, so I made sure the file name matches

    readfile = open(file, "r")

    filecontent = "" #parse through the file

    pop_count = [0,0,0,0,0,0,0,0]

    state_pop = 0 #State Total Population

    biz_pop = 0 #Total Businessmen population count
    pop_sum = 0 #Total population count *in workforce (i.e. excluding soldiers and businessmen)*

    for line in readfile: #read file line by line
        filecontent += line
        currentLine = format(line.strip()) 
        if (currentLine.find("manpower") != -1):
            state_pop = int(''.join(filter(str.isdigit, currentLine))) #I have no idea how this works, but it does, and its from stackoverflow
            
            for x in range (8):
                pop_count[x] = int((state_pop * 0.956107) * pop_type[x] * (1 - 1 / 2.033) * 0.6306) #pop_count should entail all working population
                if x == 7: #If businessmen
                    biz_pop = pop_count[x]
                elif (x != 5): #Exclude Soldiers
                    pop_sum += pop_count[x]

            #distribute biz pop
            for x in range (7):
                if (x!=5):
                    pop_count[x] += int((pop_count[x]/pop_sum)*biz_pop) #businessmen * (poptype/total working population)
                    pop_count[x] = int(round(pop_count[x]/10000)) # Divide by 10k
                #print (pop_count[x])
                #Finalized pop count in pop_count list; pop_count[5] ignored

            #editing the newly added lines in one string
            newlines = ""
            newlines += "\n\t#INDUSTRIES"
            newlines += "\n\t\t #Farmers"
            newlines += "\n\t\t set_variables = { grain_farm_size = " + str (pop_count[1]) + " }"
            newlines += "\n\t\t set_variables = { fishing_wharf_size = 0 } "
            
            newlines += "\n\n\t\t #Labourers"
            newlines += "\n\t\t set_variables = { construction_yard_size = " + str (pop_count[2]) + " }"
            newlines += "\n\t\t set_variables = { maintenance_facility_size = 0 } "

            newlines += "\n\n\t\t #Craftsmen"
            newlines += "\n\t\t set_variables = { coal_power_plant_size = " + str (pop_count[3]) + " }"
            newlines += "\n\t\t set_variables = { steel_factory_size = 0 } "
            newlines += "\n\t\t set_variables = { textile_mill_size = 0 } "
            newlines += "\n\t\t set_variables = { machinery_factory_size = 0 } "
            newlines += "\n\t\t set_variables = { chemical_plant_size = 0 } "
            newlines += "\n\t\t set_variables = { construction_materials_factory_size = 0 } "
            newlines += "\n\t\t set_variables = { silicate_materials_factory_size = 0 } "
            newlines += "\n\t\t set_variables = { automobile_factory_size = 0 } "
            newlines += "\n\t\t set_variables = { consumer_goods_factory_size = 0 } "
            newlines += "\n\t\t set_variables = { convoys_shipyard_size = 0 } "
            
            newlines += "\n\n\t\t #Clerk"
            newlines += "\n\t\t set_variables = { hospital_size = " + str (pop_count[4]) + " }"
            newlines += "\n\t\t set_variables = { school_size = 0 } "
            newlines += "\n\t\t set_variables = { university_size = 0 } "
            newlines += "\n\t\t set_variables = { shipping_hub_size = 0 } "
            newlines += "\n\t\t set_variables = { finance_hub_size = 0 } "
            newlines += "\n\t\t set_variables = { housing_office_size = 0 } "
            newlines += "\n\t\t set_variables = { entertainment_complex_size = 0 } "

            newlines += "\n\n\t\t #Professionals"
            newlines += "\n\t\t set_variables = { professional_size = " + str (pop_count[6]) + " } \n\n"

            filecontent += newlines

    #EOF
    readfile.close()

    #Overwrite the old file with the contents parsed
    writefile = open (file, "w")
    writefile.write(filecontent)
    writefile.close()        
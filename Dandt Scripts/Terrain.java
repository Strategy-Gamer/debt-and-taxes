package DandtScripts;

public class Terrain {
    public static final String[] TERRAIN_TYPES = {"Flat","Hilly","Mountainous","Dunes","Swamp","Forest"};

    public static final boolean[] TERRAIN_MODIFIERS_POSITIVE_IS_GREEN = {true,true,false,false,false,true,true,true};
    public static final String[] TERRAIN_MODIFIERS = {"Building Slots","Building Slots Modifier","Build Cost Modifier","Building Maintenance Modifier","Transport Cost Modifier","Grain Farm Output Modifier","Livestock Ranch Output Modifier","Lumber Lodge Output Modifier"};

    public static final String[] RIVER_TYPES = {"None","Miniscule","Minor","Moderate","Major","Extensive","Expansive"};
    public static final boolean[] RIVER_MODIFIERS_POSITIVE_IS_GREEN = {true,false,false,false,true};
    public static final String[] RIVER_MODIFIERS = {"Building Slots","Build Cost Modifier","Transport Cost Modifier","Shipping Cost Modifier","Grain Farm Output Modifier"};

    public static final String[] SOIL_TYPES = {"Atrocious","Poor","Below Average","Average","Above Average","Good","Excellent"};
    public static final boolean[] SOIL_MODIFIERS_POSITIVE_IS_GREEN = {true};
    public static final String[] SOIL_MODIFIERS = {"Grain Farm Output Multiplier"};

    public void terrainScriptedLocalizationScript(){
        String text = "";


        text += Dandt.newLine("#Maximum Terrain Effects", 0);
        
        //Terrain
        for(String terrain : TERRAIN_TYPES){
            String name = terrain + " Terrain Effects";
            ScriptedLoc loc = new ScriptedLoc("Get" + name);
            loc.addText(0, Dandt.toCode(name));
            text += loc.toText(1);

            for(String modifier : TERRAIN_MODIFIERS){
                name = terrain + " Terrain " + modifier;
                loc = new ScriptedLoc("Get" + name);
                loc.addText(0, "max_" + Dandt.toCode(name) + "_positive");
                loc.addTrigger(0, "check_variable = { global." + Dandt.toCode(terrain) + "_" + Dandt.toCode(modifier) + " > 0 }");
                loc.addText(1, "max_" + Dandt.toCode(name) + "_negative");
                loc.addTrigger(1, "check_variable = { global." + Dandt.toCode(terrain) + "_" + Dandt.toCode(modifier) + " < 0 }");
                text += loc.toText(1);
            }
        }
        text += Dandt.newLine("#End of Maximum Terrain Effects", 0);

        text += Dandt.newLine("#River Effects", 0);
        //Rivers
        for(int river = 0; river < 2; river++){
            String riverType = "Navigable";
            if(river == 1){
                riverType = "Unnavigable";
            }

            for(int i = 1; i < RIVER_TYPES.length; i++){
                String name = RIVER_TYPES[i] + " " + riverType + " River Effects";
                ScriptedLoc loc = new ScriptedLoc("Get" + name);
                loc.addText(0, Dandt.toCode(riverType + " River " + i + " Effects"));
                text += loc.toText(1);
    
                for(String modifier : RIVER_MODIFIERS){
                    name = RIVER_TYPES[i] + " " + riverType + " River " + modifier;
                    String variable = riverType + " River " + i + " " + modifier;
                    loc = new ScriptedLoc("Get" + name);
                    loc.addText(0, Dandt.toCode(variable) + "_positive");
                    loc.addTrigger(0, "check_variable = { global." + Dandt.toCode(variable) + " > 0 }");
                    loc.addText(1, Dandt.toCode(variable) + "_negative");
                    loc.addTrigger(1, "check_variable = { global." + Dandt.toCode(variable) + " < 0 }");
                    text += loc.toText(1);
                }
            }
        }
        text += Dandt.newLine("#End of River Effects", 0);


        //Soil
        text += Dandt.newLine("#Soil Effects", 0);
        for(int soil = 0; soil < 2; soil++){
            String soilType = "Soil Quality";
            if(soil == 1){
                soilType = "Growth Period";
            }

            for(int i = 0; i < SOIL_TYPES.length; i++){
                String name = SOIL_TYPES[i] + " " + soilType + " Effects";
                ScriptedLoc loc = new ScriptedLoc("Get" + name);
                loc.addText(0, Dandt.toCode(soilType + " " + i + " Effects"));
                text += loc.toText(1);
    
                for(String modifier : SOIL_MODIFIERS){
                    name = SOIL_TYPES[i] + " " + soilType + " " + modifier;
                    String variable = soilType + " " + i + " " + modifier;
                    loc = new ScriptedLoc("Get" + name);
                    loc.addText(0, Dandt.toCode(variable));
                    text += loc.toText(1);
                }
            }
        }
        text += Dandt.newLine("#End of Soil Effects", 0);

        //System.out.println(text);

        FileManipulator.addToFile("dandt_scripted_loc_auto.txt", "common/scripted_localisation", text);
        
        text = ""; 

        text += Dandt.newLine("#Maximum Terrain Effects", 0);
        for(String terrain : TERRAIN_TYPES){
            String key = terrain + " Terrain Effects";
            String loc = "§Y" +  terrain + " Terrain:§!\\n";
            for(String modifier : TERRAIN_MODIFIERS){
                loc += "[Get" + Dandt.noSpaces(terrain) +  "Terrain" + Dandt.noSpaces(modifier) + "]"; 
            }
            text += Dandt.newLocEntry(Dandt.toCode(key), loc, 1);
            for(int i = 0; i < TERRAIN_MODIFIERS.length; i++){
                String modifier = TERRAIN_MODIFIERS[i];
                key = terrain + " Terrain " + modifier;
                
                loc = "+[?global." + Dandt.toCode(terrain) + "_" + Dandt.toCode(modifier) + "|" + Dandt.getVariableLocEnd(key) + "]§!\\n";
                if(TERRAIN_MODIFIERS_POSITIVE_IS_GREEN[i]){
                    loc = "§G" + loc;
                }
                else{
                    loc = "§R" + loc;
                }
                text += Dandt.newLocEntry("max_" + Dandt.toCode(key) + "_positive", modifier + ": " + loc, 1);
                
                loc = "[?global." + Dandt.toCode(terrain) + "_" + Dandt.toCode(modifier) + "|" + Dandt.getVariableLocEnd(key) + "]§!\\n";
                if(TERRAIN_MODIFIERS_POSITIVE_IS_GREEN[i]){
                    loc = "§R" + loc;
                }
                else{
                    loc = "§G" + loc;
                }
                text += Dandt.newLocEntry("max_" + Dandt.toCode(key) + "_negative", modifier + ": " + loc, 1);
            }
        }
        text += Dandt.newLine("#End of Maximum Terrain Effects", 0);

        text += Dandt.newLine("#River Effects", 0);
        for(int river = 0; river < 2; river++){
            String riverType = "Navigable";
            if(river == 1){
                riverType = "Unnavigable";
            }

            for(int i = 1; i < RIVER_TYPES.length; i++){
                String key = riverType + " River " + i + " Effects";
                String loc = "§Y" + RIVER_TYPES[i] + " " + riverType + " Rivers:§!\\n";
                for(String modifier : RIVER_MODIFIERS){
                    loc += "[Get" + Dandt.noSpaces(RIVER_TYPES[i] + " " + riverType + " River " + modifier) + "]"; 
                }
                text += Dandt.newLocEntry(Dandt.toCode(key), loc, 1);
                for(int j = 0; j < RIVER_MODIFIERS.length; j++){
                    String modifier = RIVER_MODIFIERS[j];
                    key = RIVER_TYPES[j] + " " + riverType + " River " + modifier;
                    String variable = riverType + " River " + i + " " + modifier;

                    loc = "+[?global." + Dandt.toCode(variable) + "|" + Dandt.getVariableLocEnd(key) + "]§!\\n";
                    if(RIVER_MODIFIERS_POSITIVE_IS_GREEN[j]){
                        loc = "§G" + loc;
                    }
                    else{
                        loc = "§R" + loc;
                    }
                    text += Dandt.newLocEntry(Dandt.toCode(variable) + "_positive", modifier + ": " + loc, 1);
                    
                    loc = "[?global." + Dandt.toCode(variable) + "|" + Dandt.getVariableLocEnd(key) + "]§!\\n";
                    if(RIVER_MODIFIERS_POSITIVE_IS_GREEN[j]){
                        loc = "§R" + loc;
                    }
                    else{
                        loc = "§G" + loc;
                    }
                    text += Dandt.newLocEntry(Dandt.toCode(variable) + "_negative", modifier + ": " + loc, 1);
                }
            }
        }
        text += Dandt.newLine("#End of River Effects", 0);

        text += Dandt.newLine("#Soil Effects", 0);
        for(int soil = 0; soil < 2; soil++){
            String soilType = "Soil Quality";
            if(soil == 1){
                soilType = "Growth Period";
            }

            for(int i = 0; i < SOIL_TYPES.length; i++){
                String key = soilType + " " + i + " Effects";
                String loc = "§Y" + SOIL_TYPES[i] + " " + soilType + ":§!\\n";
                for(String modifier : SOIL_MODIFIERS){
                    loc += "[Get" + Dandt.noSpaces(SOIL_TYPES[i] + " " + soilType + " " + modifier) + "]"; 
                }
                text += Dandt.newLocEntry(Dandt.toCode(key), loc, 1);
                for(int j = 0; j < SOIL_MODIFIERS.length; j++){
                    String modifier = SOIL_MODIFIERS[j];
                    key = SOIL_TYPES[j] + " " + soilType + " " + modifier;
                    String variable = soilType + " " + i + " " + modifier;
                    
                    loc = "§Y[?global." + Dandt.toCode(variable) + "|" + Dandt.getVariableLocEnd(key) + "]x§!\\n";
                    text += Dandt.newLocEntry(Dandt.toCode(variable), modifier + ": " + loc, 1);
                }
            }
        }
        text += Dandt.newLine("#End of Soil Effects", 0);

        FileManipulator.addToFile("MOD_dandt_auto_l_english.yml", "localisation", text);
    }
}

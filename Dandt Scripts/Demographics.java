package DandtScripts;

public class Demographics {

    //For these lists: ALWAYS ADD TO THE END. Do not change the order
    //First one will always be the undefined one. Just incase b/c y'now, incompetent devs. (Or me being an idiot, or a dev being an idiot)
    public static final Demographic CULTURE_LIST[] = {
        new Demographic("Undefined Culture",127,127,127),
        new Demographic("Pacificer",75,255,249),
        new Demographic("Pioneer",231,114,0),
        new Demographic("Canadian",0,231,49),
        new Demographic("Quebecois",0,54,231),
        new Demographic("Yankee",0,114,231),
        new Demographic("Appalachian",236,222,101),
        new Demographic("Dixie",184,184,184),
        new Demographic("African-American",87,87,87),
        new Demographic("El Norte",151,241,115),
        new Demographic("Mexican",57,154,60),
        new Demographic("Amerindian",147,43,141),
        new Demographic("Pacific Islander",255,129,215),
        new Demographic("Ashkenazi",189,234,251),
        new Demographic("Japanese",255,191,191),
        new Demographic("English",219,83,102),
        new Demographic("Scottish",50,107,184),
        new Demographic("Welsh",73,183,111),
        new Demographic("Irish",71,171,69),
        new Demographic("Dutch",217,156,94),
        new Demographic("Flemish",244,192,118),
        new Demographic("Walloon",101,88,146),
        new Demographic("French",31,87,237),
        new Demographic("Breton",210,199,219),
        new Demographic("Catalan",235,164,111),
        new Demographic("Basque",255,220,137),
        new Demographic("Castillian",213,197,60),
        new Demographic("Andalucian",168,224,160),
        new Demographic("Galician",212,142,142),
        new Demographic("Portugese",115,200,59),
        new Demographic("North Italian",66,220,91),
        new Demographic("South Italian",102,174,114),
        new Demographic("North German",86,71,68),
        new Demographic("South German",116,94,94),
        new Demographic("Danish",220,83,83),
        new Demographic("Norwegian",172,113,70),
        new Demographic("Swedish",56,114,186),
        new Demographic("Finnish",198,186,194),
        new Demographic("Icelandic",143,172,187),
        new Demographic("Slovenian",66,135,158),
        new Demographic("Croatian",168,160,206),
        new Demographic("Bosniak",83,211,207),
        new Demographic("Serbian",184,102,102),
        new Demographic("Albanian",172,47,22),
        new Demographic("Macedonian",23,50,111),
        new Demographic("Greek",110,207,231),
        new Demographic("Bulgarian",82,160,89),
        new Demographic("Turkish",234,65,32),
        new Demographic("Kurdish",224,192,107),
        new Demographic("Romanian",234,229,106),
        new Demographic("Romani",0,128,255),
        new Demographic("Hungarian",236,192,124),
        new Demographic("Slovakian",166,209,216),
        new Demographic("Czech",87,226,247),
        new Demographic("Polish",243,162,146),
        new Demographic("Lithuanian",120,207,103),
        new Demographic("Latvian",175,82,104),
        new Demographic("Estonian",156,236,231),
        new Demographic("Byelorussian",146,166,155),
        new Demographic("Ukranian",182,184,108),
        new Demographic("Russian",67,117,74),
        new Demographic("Tatars",109,136,144),
        new Demographic("Bashkir",61,203,81),
        new Demographic("Caucasian",195,208,82),
        new Demographic("Georgian",141,201,141),
        new Demographic("Armenian",180,132,178),
        new Demographic("Azeri",42,48,107),
        new Demographic("Mongol",229,230,43),
        new Demographic("Siberian",132,193,232)
    };
    public static final Demographic RELIGION_LIST[] = {
        new Demographic("Undefined Religion",127,127,127),
        new Demographic("Irreligious",184,184,184),
        new Demographic("Catholic",248,240,17),
        new Demographic("Protestant",57,154,237),
        new Demographic("Orthodox",217,148,39),
        new Demographic("Mormon",168,107,177),
        new Demographic("Coptic",126,108,73),
        new Demographic("Judaism",189,234,251),
        new Demographic("Sunni",42,183,39),
        new Demographic("Shia",77,229,74),
        new Demographic("Ibadi",65,136,63),
        new Demographic("Vajrayana",255,108,186),
        new Demographic("Mahayana",251,128,113),
        new Demographic("Thervada",255,108,186),
        new Demographic("Hindu",252,171,79),
        new Demographic("Sikh",180,88,3),
        new Demographic("Confucianism",255,237,111),
        new Demographic("Shinto",179,30,30),
        new Demographic("Indigenous",75,185,186),
        new Demographic("African Folk",87,87,87)
    };
    public static final Demographic IDEOLOGY_LIST[] = {
        new Demographic("Undefined Ideology",127,127,127),
        new Demographic("Apolitical",87,87,87),
        new Demographic("Communist",166,0,0),
        new Demographic("Socialist",200,120,120),
        new Demographic("Liberal",215,130,65),
        new Demographic("Libertarian",184,165,18),
        new Demographic("Conservative",50,134,217),
        new Demographic("Reactionary",39,95,140),
        new Demographic("Fascist",153,83,15)
    };

    /**
     * Creates scripted localization and creates the gfx entries for cultures, religions, and ideologies
     * 
     * @param type The type. Can be either "Culture", "Religion", or "Ideology"
     * @param demographicList The list of cultures/religions/ideologies to be put in the localization
     */
    public static void demographicsCreator(String type, Demographic[] demographicList){
        //Scripted Loc
        String text = "";
        text += Dandt.newLine("#" + type, 0);

        ScriptedLoc nameLoc = new ScriptedLoc("GetValueTo" + type + "Name");
        for(int i = 0; i < demographicList.length; i++){
            if(i==0){
                nameLoc.addText(9999, "\"" + demographicList[i].getName() + "\"");
            }
            else{
                nameLoc.addText(i + 1, "\"" + demographicList[i].getName() + "\"");
                nameLoc.addTrigger(i + 1, "state = " + (i + 1));
            }
        }
        text += nameLoc.toText(1);
        //text += Dandt.newLine("#GetValueTo" + type + "Color#", 1);
        //for(int j = 0; j < 100; j++){
            ScriptedLoc colorLoc = new ScriptedLoc("GetValueTo" + type + "Color");
            for(int i = 0; i < demographicList.length; i++){
                if(i==0){
                    colorLoc.addText(9999, "\"GFX_" + Dandt.toCode(demographicList[i].getName()) + "_pie_chart_segment\"");
                }
                else{
                    colorLoc.addText(i + 1, "\"GFX_" + Dandt.toCode(demographicList[i].getName()) + "_pie_chart_segment\"");
                    //colorLoc.addTrigger(i + 1, "check_variable = { pie_chart_" + Dandt.toCode(type) + "^" + j + " = " + (i + 1) +" }");
                    colorLoc.addTrigger(i + 1, "state = " + (i + 1));
                }
            }
            text += colorLoc.toText(1);
        //}
        //for(int j = 0; j < 100; j++){
            colorLoc = new ScriptedLoc("GetValueTo" + type + "ColorSquare");
            for(int i = 0; i < demographicList.length; i++){
                if(i==0){
                    colorLoc.addText(9999, "\"GFX_" + Dandt.toCode(demographicList[i].getName()) + "_square_color\"");
                }
                else{
                    colorLoc.addText(i + 1, "\"GFX_" + Dandt.toCode(demographicList[i].getName()) + "_square_color\"");
                    //colorLoc.addTrigger(i + 1, "check_variable = { pie_chart_" + Dandt.toCode(type) + "^" + j + " = " + (i + 1) +" }");
                    colorLoc.addTrigger(i + 1, "state = " + (i + 1));
                }
            }
            text += colorLoc.toText(1);
        //}
        text += Dandt.newLine("##################################################################################################################", 0);

        for(int i = 0; i < demographicList.length; i++){
            text += Dandt.newLines(
                String.join("\n", 
                "spriteType = {",
                "\tname = \"GFX_" + Dandt.toCode(demographicList[i].getName()) + "_pie_chart_segment\"",
                "\ttexturefile = \"gfx//interface//graphs//piecharts//" + Dandt.toCode(demographicList[i].getName()) + "_pie_chart_segment.png\"",
                "}"
            ), 1);
            text += Dandt.newLines(
                String.join("\n", 
                "spriteType = {",
                "\tname = \"GFX_" + Dandt.toCode(demographicList[i].getName()) + "_square_color\"",
                "\ttexturefile = \"gfx//interface//graphs//piecharts//" + Dandt.toCode(demographicList[i].getName()) + "_square_colour.png\"",
                "}"
            ), 1);
            text += Dandt.newLines("",0);
        }
        text += Dandt.newLine("##################################################################################################################", 0);

        text += Dandt.newLine("add_to_array = { global." + Dandt.toCode(type) + "_color_array = 0 }", 2);
        for(int i = 0; i < demographicList.length; i++){
            Demographic d = demographicList[i];
            long state_cat_pos = Math.round(d.getColor().getRed() / 32.0) + 9 * Math.round(d.getColor().getGreen() / 32.0) + 81 * Math.round(d.getColor().getBlue() / 32.0);
            text += Dandt.newLine("add_to_array = { global." + Dandt.toCode(type) + "_color_array = " + state_cat_pos + " }", 2);
            FileManipulator.exportImage(Dandt.applyColor(FileManipulator.getGraphicsImage("zzz_pie_chart_segment", "DandtScripts/Folder"), demographicList[i].getColor()), Dandt.toCode(demographicList[i].getName()) + "_pie_chart_segment", "DandtScripts/Folder");
            FileManipulator.exportImage(Dandt.applyColor(FileManipulator.getGraphicsImage("zzz_square_colour", "DandtScripts/Folder"), demographicList[i].getColor()), Dandt.toCode(demographicList[i].getName()) + "_square_colour", "DandtScripts/Folder");
        }

        text += Dandt.newLine("##################################################################################################################", 0);
        
        text += Dandt.newLines("#Value\t\t#" + type, 1);
        text += Dandt.newLines("-\t\t\t" + demographicList[0].getName(), 1);
        for(int i = 1; i < demographicList.length; i++){
            text += Dandt.newLines((i + 1) + "\t\t\t" + demographicList[i].getName(), 1);
        }

        FileManipulator.writeToFile("output.txt", "DandtScripts", text);

    }

    /**
     * Creates colors for mapmodes
     * 
     * @param type The type. Can be either "Culture", "Religion", or "Ideology"
     * @param demographicList The list of cultures/religions/ideologies
     */
    public static void createColorCode(String type, Demographic[] demographicList){
        String text = "";
        text += Dandt.newLine("#" + type, 2);

        text += Dandt.newLine("add_to_array = { global." + Dandt.toCode(type) + "_r_array = 0 }", 2);
        text += Dandt.newLine("add_to_array = { global." + Dandt.toCode(type) + "_g_array = 0 }", 2);
        text += Dandt.newLine("add_to_array = { global." + Dandt.toCode(type) + "_b_array = 0 }", 2);
        for(int i = 0; i < demographicList.length; i++){
            Demographic d = demographicList[i];
            text += Dandt.newLine("", 2);
            text += Dandt.newLine("add_to_array = { global." + Dandt.toCode(type) + "_r_array = " + d.getColor().getRed() + " }", 2);
            text += Dandt.newLine("add_to_array = { global." + Dandt.toCode(type) + "_g_array = " + d.getColor().getGreen() + " }", 2);
            text += Dandt.newLine("add_to_array = { global." + Dandt.toCode(type) + "_b_array = " + d.getColor().getBlue() + " }", 2);
        }
        FileManipulator.writeToFile("output.txt", "DandtScripts", text);
    }
}

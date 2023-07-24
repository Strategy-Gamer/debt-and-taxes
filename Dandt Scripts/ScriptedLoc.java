package DandtScripts;

import java.util.TreeMap;

public class ScriptedLoc {

    String name;

    private TreeMap<Integer, String> texts;
    private TreeMap<Integer, String> triggers;

    public ScriptedLoc(String name){
        this.name = name;
        texts = new TreeMap<Integer, String>();
        triggers = new TreeMap<Integer, String>();
    }

    public void addText(int num, String localization_key){
        texts.put(num, localization_key);
    }

    public void addTrigger(int num, String trigger){
        if(triggers.get(num) == null){
            triggers.put(num, trigger);
        }
        else{
            triggers.put(num, triggers.get(num) + "\n" + trigger);
        }
    }

    /**
     * Returns the scripted localization as would fit HOI4's scripted loc file
     * @return Scripted localization code for HOI4
     */
    public String toText(int additionalTabs){

        String text = Dandt.newLines("#" + Dandt.noSpaces(name), additionalTabs);
        text += Dandt.newLines("defined_text = {", additionalTabs + 1);
        text += Dandt.newLines("name = " + Dandt.noSpaces(name), additionalTabs + 2);
        for(Integer key : texts.keySet()){
            text += Dandt.newLines("text = {", additionalTabs + 2);
            if(triggers.get(key) != null){
                text += Dandt.newLines("trigger = {", additionalTabs + 3);
                text += Dandt.newLines(triggers.get(key), additionalTabs + 4);
                text += Dandt.newLines("}", additionalTabs + 3);
            }
            text += Dandt.newLine("localization_key = " + texts.get(key), additionalTabs + 3);
            text += Dandt.newLines("}", additionalTabs + 2);
        }
        text += Dandt.newLines("}", additionalTabs + 1);
        /*
        What it should look like:
        #this.name
            defined_text = {
                name = this.name
                text = {
                    trigger = {
                        triggers.get(key)
                    }
                    localization_key = texts.get(key)
                }
            }
        */
        return text;
    }

}

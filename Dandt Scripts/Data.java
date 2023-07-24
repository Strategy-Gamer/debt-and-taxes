package DandtScripts;

public class Data {
    public static final String[] RESOURCES = {
        "Oil",
        "Aluminum",
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
        "Rare Earth Metals",
        "Cement",
        "Silicates",
        "Chemicals",
        "Fabric",
        "Machinery",
        "Plastics",
        "Fertilizer",
        "Hydrocarbons",
        "Automobiles",
        "Airplanes",
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
        "Ammunition",
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
    };

    public static final String[] POP_TYPES = {
        "Slaves", //1
        "Unskilled",
        "Skilled",
        "Educated",
        "Soldiers",
        "Bureaucrats",
        "Capitalists",
        "Retirees"
    };

    public static final IndustryType[] INDUSTRIES = {
        new IndustryType("Oil Rig", Data.RESOURCES[0]),
        new IndustryType("Bauxite Mine", Data.RESOURCES[1]),
        new IndustryType("Rubber Lodge", Data.RESOURCES[2]),
        new IndustryType("Synthetic Rubber Plant", Data.RESOURCES[2]),
        new IndustryType("Tungsten Mine", Data.RESOURCES[3]),
        new IndustryType("Iron Mine", Data.RESOURCES[4]),
        new IndustryType("Chromium Mine", Data.RESOURCES[5]),
        new IndustryType("Copper Mine", Data.RESOURCES[6]),
        new IndustryType("Precious Metals Mine", Data.RESOURCES[7]),
        new IndustryType("Coal Mine", Data.RESOURCES[8]),
        new IndustryType("Lumber Lodge", Data.RESOURCES[9]),
        new IndustryType("Cotton Plantation", Data.RESOURCES[10]),
        new IndustryType("Sheep Ranch", Data.RESOURCES[10]),
        new IndustryType("Grain Farm", Data.RESOURCES[11]),
        new IndustryType("Livestock Ranch", Data.RESOURCES[11]),
        new IndustryType("Fishing Wharf", Data.RESOURCES[11]),
        new IndustryType("Coal Power Plant", Data.RESOURCES[12]),
        new IndustryType("Oil Power Plant", Data.RESOURCES[12]),
        new IndustryType("Nuclear Power Plant", Data.RESOURCES[12]),
        new IndustryType("Wind Farm", Data.RESOURCES[12]),
        new IndustryType("Solar Farm", Data.RESOURCES[12]),
        new IndustryType("Hydroelectric Dam", Data.RESOURCES[12]),
        new IndustryType("Steel Mill", Data.RESOURCES[13]),
        new IndustryType("Rare Earth Metals Mine", Data.RESOURCES[14]),
        new IndustryType("Cement Factory", Data.RESOURCES[15]),
        new IndustryType("Silicates Factory", Data.RESOURCES[16]),
        new IndustryType("Chemical Plant", Data.RESOURCES[17]),
        new IndustryType("Textile Mill", Data.RESOURCES[18]),
        new IndustryType("Machinery Factory", Data.RESOURCES[19]),
        new IndustryType("Plastics Factory", Data.RESOURCES[20]),
        new IndustryType("Fertilizer Factory", Data.RESOURCES[21]),
        new IndustryType("Oil Refinery", Data.RESOURCES[22]),
        new IndustryType("Synthetic Oil Refinery", Data.RESOURCES[22]),
        new IndustryType("Automobile Factory", Data.RESOURCES[23]),
        new IndustryType("Airplane Factory", Data.RESOURCES[24]),
        new IndustryType("Computer Factory", Data.RESOURCES[25]),
        new IndustryType("Electronics Factory", Data.RESOURCES[26]),
        new IndustryType("Food Processing Plant", Data.RESOURCES[27]),
        new IndustryType("Pharmaceuticals Factory", Data.RESOURCES[28]),
        new IndustryType("Clothing Factory", Data.RESOURCES[29]),
        new IndustryType("Consumer Goods Factory", Data.RESOURCES[30]),
        new IndustryType("Wooden Convoys Shipyard", Data.RESOURCES[31]),
        new IndustryType("Convoys Shipyard", Data.RESOURCES[32]),
        new IndustryType("Small Arms Factory", Data.RESOURCES[33]),
        new IndustryType("Artillery Factory", Data.RESOURCES[34]),
        new IndustryType("Tank Factory", Data.RESOURCES[35]),
        new IndustryType("Ammunition Factory", Data.RESOURCES[36]),
        new IndustryType("Rocket Factory", Data.RESOURCES[37]),
        new IndustryType("Construction Yard", Data.RESOURCES[38]),
        new IndustryType("Maintenance Facility", Data.RESOURCES[39]),
        new IndustryType("Hospital", Data.RESOURCES[40]),
        new IndustryType("School", Data.RESOURCES[41]),
        new IndustryType("University", Data.RESOURCES[42]),
        new IndustryType("Transport Hub", Data.RESOURCES[43]),
        new IndustryType("Shipping Hub", Data.RESOURCES[44]),
        new IndustryType("Finance Hub", Data.RESOURCES[45]),
        new IndustryType("Housing Office", Data.RESOURCES[46]),
        new IndustryType("Entertainment Complex", Data.RESOURCES[47])
    };

    public static String[] getIndustryNameList(){
        String[] str = new String[INDUSTRIES.length];
        for(int i = 0; i < INDUSTRIES.length; i++){
            str[i] = INDUSTRIES[i].name;
        }
        return str;
    }
    public static String[] getIndustryResourceList(){
        String[] str = new String[INDUSTRIES.length];
        for(int i = 0; i < INDUSTRIES.length; i++){
            str[i] = INDUSTRIES[i].resource;
        }
        return str;
    }
}

#include<iostream>
#include<fstream>
#include<sstream>
#include<vector>
#include<string>
using namespace std;

static vector<string> industry_tokens = {
    "industry_grain_farm",
    "industry_rice_paddy",
    "industry_livestock_ranch",
    "industry_fishing_wharf",
    "industry_sheep_ranch",
    "industry_lumber_lodge",
    "industry_cotton_plantation",
    "industry_silk_plantation",
    "industry_oil_rig",
    "industry_gas_rig",
    "industry_offshore_oil_rig",
    "industry_bauxite_mine",
    "industry_rubber_lodge",
    "industry_tungsten_mine",
    "industry_iron_mine",
    "industry_chromium_mine",
    "industry_coal_mine",
    "industry_copper_mine",
    "industry_precious_metals_mine",
    "industry_rare_earth_metals_mine",
    "industry_sulfur_mine",
    "industry_coal_power_plant",
    "industry_oil_power_plant",
    "industry_nuclear_power_plant",
    "industry_wind_power_farm",
    "industry_solar_power_Farm",
    "industry_hydroelectric_dam",
    "industry_steel_mill",
    "industry_cement_factory",
    "industry_silicates_factory",
    "industry_chemical_plant",
    "industry_textile_mill",
    "industry_machinery_factory",
    "industry_plastics_factory",
    "industry_fertilizer_factory",
    "industry_oil_refinery",
    "industry_synthetic_oil_refinery",
    "industry_automobile_Factory",
    "industry_airplane_factory",
    "industry_computer_factory",
    "industry_electronics_factory",
    "industry_food_processing_plant",
    "industry_pharmaceuticals_factory",
    "industry_clothing_factory",
    "industry_consumer_goods_factory",
    "industry_convoys_shipyard",
    "industry_small_arms_factory",
    "industry_artillery_factory",
    "industry_tank_factory",
    "industry_ammunition_factory",
    "industry_rocket_factory",
    "industry_tools_factory",
    "industry_real_estate",
    "industry_construction_yard",
    "industry_hospital",
    "industry_school",
    "industry_university",
    "industry_transport_hub",
    "industry_shipping_hub",
    "industry_finance_hub",
    "industry_entertainment_complex"
};

static vector<string> resource_tokens = {
    "resource_crude_oil",
    "resource_aluminum",
    "resource_rubber",
    "resource_tungsten",
    "resource_iron",
    "resource_chromium",
    "resource_copper",
    "resource_precious_metals",
    "resource_coal",
    "resource_lumber",
    "resource_fiber",
    "resource_foodstuffs",
    "resource_steel",
    "resource_electricity",
    "resource_rare_earth_metals",
    "resource_cement",
    "resource_silicates",
    "resource_chemicals",
    "resource_fabric",
    "resource_machinery",
    "resource_plastics",
    "resource_fertilizer",
    "resource_hydrocarbons",
    "resource_automobiles",
    "resource_airplanes",
    "resource_computers",
    "resource_electronics",
    "resource_groceries",
    "resource_pharmaceuticals",
    "resource_clothing",
    "resource_consumer_goods",
    "resource_sulfur",
    "resource_convoys",
    "resource_small_arms",
    "resource_artillery",
    "resource_tanks",
    "resource_ammunition",
    "resource_rockets",
    "resource_construction",
    "resource_tools",
    "resource_healthcare",
    "resource_basic_education",
    "resource_higher_education",
    "resource_transport",
    "resource_shipping",
    "resource_finance",
    "resource_housing",
    "resource_entertainment"
};

int main(int argc, char *argv[]){
    
    string file = "";
    ofstream of;
    
    if(argc>=2){
        file = argv[1];
    }
    else{
        file = "output.txt";
    }

    of.open(file); //Open File
    if(!of.is_open()){
        cerr << "Error while opening file " << file << endl;
        return 1;
    }

    for(unsigned int i = 0; i < resource_tokens.size(); i++){
        of << "#GetResourcePriceColor_" << resource_tokens[i] << endl;
        of << "\tdefined_text = { " << endl;
        of << "\t\tname = GetResourcePriceColor_" << resource_tokens[i] << endl;
        of << "\t\ttext = { " << endl;
        of << "\t\t\ttrigger = { " << endl;
        of << "\t\t\t\tset_temp_variable = { grpc_temp = global.base_price@token:" << resource_tokens[i] << " }" << endl;
        of << "\t\t\t\tmultiply_temp_variable = { grpc_temp = 0.9 }" << endl;
        of << "\t\t\t\tcheck_variable = { selected_state:price@token:" << resource_tokens[i] << " < grpc_temp }" << endl;
        of << "\t\t\t}" << endl;
        of << "\t\t\tlocalization_key = \"§G\"" << endl;
        of << "\t\t}" << endl;
        of << "\t\ttext = { " << endl;
        of << "\t\t\ttrigger = { " << endl;
        of << "\t\t\t\tset_temp_variable = { grpc_temp = global.base_price@token:" << resource_tokens[i] << " }" << endl;
        of << "\t\t\t\tmultiply_temp_variable = { grpc_temp = 1.1 }" << endl;
        of << "\t\t\t\tcheck_variable = { selected_state:price@token:" << resource_tokens[i] << " > grpc_temp }" << endl;
        of << "\t\t\t}" << endl;
        of << "\t\t\tlocalization_key = \"§R\"" << endl;
        of << "\t\t}" << endl;
        of << "\t\ttext = { " << endl;
        of << "\t\t\tlocalization_key = \"§Y\"" << endl;
        of << "\t\t}" << endl;
        of << "\t}" << endl;
        of << "#" << endl;
    }

    of.close();
}
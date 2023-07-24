
#Red to Purple
num = 0
while num <= 10:
  print("")
  print("###################################################### " + str(num) + " ######################################################")
  state = 1
  while state <= 907:
    print("\thighlight_red_to_purple_" + str(num) + "_" + str(state) + " = {")
    print("\t\ticon = GFX_mapmodes_highlight_red_to_purple_" + str(num))
    print("")
    print("\t\thighlight_states = {")
    print("\t\t\tstate = " + str(state))
    print("\t\t}")
    print("\t\ton_map_mode = map_only")
    print("\t\tvisible = {")
    print("\t\t\t" + str(state) + " = {")
    print("\t\t\t\thas_state_flag = highlight_red_to_purple_"+ str(num))
    print("\t\t\t}")
    print("\t\t}")
    print("\t}")
    state += 1
  num += 1
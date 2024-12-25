 readarray GREPPED < <(grep "some expression" index.txt)
 for item in "${GREPPED[@]}"
 do
     # echo
     echo "${item}"   
 done

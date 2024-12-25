#!/bin/bash

acc=0
toggle=true
readarray muls < <(grep -o "mul([0-9]\+,[0-9]\+)\|do()\|don't()" day3_in.txt)
for sel in "${muls[@]}"
do
    # echo "${sel}"
    if [[ "$sel" = "do()"* ]]; then
      toggle=true
    elif [[ "$sel" = "don't()"* ]]; then
      toggle=false
    elif [[ "$toggle" == true ]]; then 
      readarray facts < <(grep -o "[0-9]\+" <<< "${sel}")
      # echo "${facts[0]} ${facts[1]}"
      acc=$(("${facts[0]}" * "${facts[1]}" + acc))
    fi
done
cowthink -f reindeer "$acc"

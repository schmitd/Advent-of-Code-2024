#!/usr/bin/env ruby
arr_a = Array.new
arr_b = Array.new

ARGF.each_line do |line|
  a, b = line.split
  arr_a << a.to_i
  arr_b << b.to_i
end

arr_a.sort!
arr_b.sort!
pairs = arr_a.zip arr_b
total_distance = 0
pairs.each {|a, b| total_distance += (a-b).abs}

# print result to stdout
puts total_distance

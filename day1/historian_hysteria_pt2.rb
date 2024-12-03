
#!/usr/bin/env ruby

arr_a = Array.new
arr_b = Array.new
ARGF.each_line do |line|
  a, b = line.split
  arr_a << a.to_i
  arr_b << b.to_i
end

freqs = Hash[ arr_b.group_by{|b| b}.map{|k, v| [k, v.size]} ]
freqs.default = 0
similarity_score = arr_a.inject(0) { |acc, a| acc + (a * freqs[a]) }
puts similarity_score

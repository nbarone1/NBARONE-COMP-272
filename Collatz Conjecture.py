# collatz problem

import timing

n = 2<<1000000
n = n-1
steps = 0

while n !=1:
	if (n&1 == 0):
		n=n>>1
		steps += 1
	else:
		n = (n<<1+n+1)>>1
		steps += 2
print(steps)
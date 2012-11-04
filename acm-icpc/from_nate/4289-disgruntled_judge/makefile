all: even_lcg_fast even_lcg

even_lcg: even_lcg.cpp
	g++ -Wall -o even_lcg even_lcg.cpp

even_lcg_fast: even_lcg_fast.cpp
	g++ -Wall -o even_lcg_fast even_lcg_fast.cpp

run: even_lcg_fast
	./even_lcg_fast

clean:
	rm -rf even_lcg even_lcg_fast


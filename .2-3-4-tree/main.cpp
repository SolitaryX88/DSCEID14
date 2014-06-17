#include <cstdlib>
#include "tree234.h"
#include <iostream>
#include <vector>
using namespace std;


int main(int argv,  char** argc)
{
	int x;
    vector<int> v { 10, 20, 30, 40, 50 ,60, 70 , 80, 90, 37, 36, 35, 34, 1 };

    Tree234<int> tree;

    cout << "----------------2 3 4 Tree------------------" << endl;


    for (auto iter = v.begin(); iter != v.end(); ++iter)     {
        
        tree.insert(*iter);
    }
    // Searching
    cout << "Please give a number for searching : " << endl;
    		// cout << "Problem "<< endl;
    cin >> x ;
    tree.search(x);
    // End of Searching

    tree.traverse([](int x){ cout << x << ' '; }); 
    cout << endl;


    cout << "----------------End-------------------------" << endl;
    return 0;
}



#include <cstdlib>
#include "tree234.h"
#include <iostream>
#include <vector>
using namespace std;



int main(int argc, char** argv)
{
    vector<int> v { 10, 20, 30, 40, 50 ,60, 70 , 80, 90, 37, 36, 35, 34 };

    int size;
    size = sizeof(v);
    for (int i=0; i < size; i++)
    {
    cout << v[i] << ' ';
    }
    cout<< endl;


    Tree234<int> tree;

    for (auto iter = v.begin(); iter != v.end(); ++iter)     {

        tree.insert(*iter);
    }

int reply;
bool goodbye = false;

do {

 cout<<"1. Search\n";
 cout<<"2. Insert\n";
 cout<<"3. Delete\n";
 cout<<"4. Number of Nodes\n";
 cout<<"5. Exit\n";
 cout<<"Selection: ";
cin>> reply;
switch (reply)
{

    case 1:
    // Search process
    int key;
    cout << "Insert Search KEY : " << endl;
    cin>> key;
    for (auto searchkey = v.begin(); searchkey != v.end(); searchkey++) {

    }
    if (tree.search(key) == true)
        	{
        		cout << "Search Number Found :-)" <<endl;
        	}
    else
    	{
    			cout << "Nothing Found :-(" <<endl;
    	}
    break;

    case 2:
    // Insert process
    	cout << "Insert new integer : " << endl;
    	int a;
    	cin>>a;
    	tree.insert(a);
    break;

    case 3:
    //Delete process
    	cout << "Delete integer : " << endl;
    	int b;
    	cin>>b;
    	tree.remove(b);
    	{cout << b << ' ';}
    	cout << endl;
    	break;

    case 4:

    	break ;
    case 5:
    	cout<<"Bye bye!\n";
    	goodbye = true;
    	break;
}

} while (goodbye == false);

    // End of search process

    /* Using a lambda below is equivalent to doing

        void print_int(int x)  { cout << x << ' '; }
        tree.traverse(print_int);
    */

    tree.traverse([](int x){ cout << x << ' '; });
    cout << endl;



    return 0;
}

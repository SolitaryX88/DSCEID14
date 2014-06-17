#ifndef TREE234_H
#define	TREE234_H


#include <utility>
#include <iostream>
#include <exception>
#include <cstdlib>

// fwd declarations
template<typename T> class Tree234;    
template<typename K> class Node234; 



class duplicatekey :  public std::exception {
public:
    
  virtual const char* what() const throw()
  {
    return "Attempting to insert duplicate key ";
  }
};

template<typename K> class Tree234 {
  public:
  class Node234; // forward declaration of nested class.
      
  protected:

    Node234 *root; 
    
    bool DoSearch(K key, Node234 *&location, int& index);
    template<typename Functor> void DoTraverse(Functor f, Node234 *root);
    Node234 *getNextChild(Node234 *current, K key);
    void split(Node234 *node);
    void DestroyTree(Node234 *root);

  public:

   
   class Node234 {
      private: 
       friend class Tree234<K>;             
       static int MAX;   
       Node234(K small);
       Node234(K small, K larger);
       Node234(K small, K middle, K large);

       Node234 *parent;
       
       int totalItems; /* If totalItems is 1, then two node; if 2, then three node, if 3, then four node. */   
       K keys[3];
       Node234 *children[4];

       bool find(K key, int& index);
       int insertItem(K key);
       bool isFull();
       Node234 *getParent();
       bool isLeaf(); 
       void connectChild(int childNum, Node234 *child);

    };  
    typedef Node234 Node;

     Tree234() { root = nullptr; } 
     ~Tree234(); 
     bool search(K key);
     bool remove(K key, Node234 *location=0);
     template<typename Functor> void traverse(Functor f);
     void insert(K key); // throw(duplicatekey) 
};

template<typename K> int  Tree234<K>::Node234::MAX = 3;

template<typename K> inline  Tree234<K>::Node234::Node234(K small) : totalItems(1)
{ 
   keys[0] = small; 

   for (int i = 0; i < MAX; i++) {		
       children[i] = nullptr;
    }
}

template<typename K> inline  Tree234<K>::Node234::Node234(K small, K middle) : totalItems(2)
{ 
   keys[0] = small; 
   keys[1] = middle; 

   for (int i = 0; i < MAX; i++) {		
       children[i] = nullptr;
    }
}

template<typename K> inline  Tree234<K>::Node234::Node234(K small, K middle, K large) : totalItems(3)
{ 
   keys[0] = small; 
   keys[1] = middle; 
   keys[3] = large; 
    
   for (int i = 0; i < MAX; i++) {		
       children[i] = nullptr;
    }
}

template<typename K> inline void  Tree234<K>::Node234::connectChild(int childNum, Node234 *child)
{
  children[childNum] = child;
  child->parent = this;
}

template<typename K> inline int  Tree234<K>::Node234::insertItem(K key)
{ 
  // start on right, examine items

  for(int i = totalItems - 1; i >= 0 ; i--) {


        if (key < keys[i]) { // if key[i] is bigger

            keys[i + 1] = keys[i]; // shift it right

        } else {

            keys[i + 1] = key; // insert new item
          ++totalItems;        // increase the total item count
            return i + 1;      // return index to inserted key.
        } 
    } 

    // shifted all items, insert new item at position 0

    keys[0] = key;  
  ++totalItems; // increase the total item count
    return 0;



template<typename K> inline  bool Tree234<K>::Node234::isFull()  
{ 
   return totalItems == MAX;
}

template<typename K> inline  typename Tree234<K>::Node234 *Tree234<K>::Node234::getParent()  
{ 
   return parent;
}
template<typename K> inline  bool Tree234<K>::Node234::isLeaf()  
{ 
   return !children[0] ? true : false;
}

template<typename K> inline bool Tree234<K>::Node234::find(K key, int& index)
{ 
   for(int i = 0; i < totalItems; i++) {
   
       if (keys[i] == key) {

            index = i;
            return true;
       }
   }
   return false;
}

template<typename K> inline Tree234<K>::~Tree234()
{
   DestroyTree(root);
}


template<typename K> void Tree234<K>::DestroyTree(Node234 *current)
{
  if (current == nullptr) {

	return;
   }

   switch (current->totalItems) {

      case 1: // two node
            DestroyTree(current->children[0]);

            DestroyTree(current->children[1]);

            delete current;

            break;

      case 2: // three node
            DestroyTree(current->children[0]);

            DestroyTree(current->children[1]);
 
            DestroyTree(current->children[2]);

            delete current;

            break;

      case 3: // four node
            DestroyTree(current->children[0]);

            DestroyTree(current->children[1]);
 
            DestroyTree(current->children[2]);

            DestroyTree(current->children[3]);

            delete current;
 
            break;
   }
  
}

template<typename K> bool Tree234<K>::search(K key)
{
    // make sure tree has at least one element    
    if (root == nullptr) {
    		// Start of my code
    		{
    			bool empty = true;
    			using namespace std;
    			cout <<  "Empty :" << empty <<endl;
    			cout << "Tree has no elements" << endl;
    		}
    		// End of my code
       return false;
        
    } else {
        int index;  
        Node234 *location;
        {
                	using namespace std;
                	cout << "Key : " << key << " Location : "<<location << " Index : " << index << endl;
        }
        return DoSearch(key, location, index);

    }
}   
late<typename K>  bool Tree234<K>::DoSearch(K key, Node234 *&location, int& index)
{
  Node234 *current = root;

  if (root == nullptr) {
	  {
	   		using namespace std;
	   		cout <<  "There aren't this element" <<endl;
	  }
     return false;
  }

  while(true) {
 
      if (current->find(key, index)) {

          location = current;
          //Start print Code
          {
          using namespace std;
          bool contains = true ;
          cout << "H timi pou zitithike emfanizetai, \n i metavliti contains exei tin timi " << contains <<endl;
          }
          //End Code
          return true; 

      } else if (current->isLeaf()) {
    	  //Start print Code
    	  {
    	  using namespace std;
    	  bool contains = false ;
    	  cout << "H timi pou zitithike den emfanizetai, \n i metavliti contains exei tin timi " << contains <<endl;
    	   }
    	  //End Code
          return false;

      } else {

          current = getNextChild(current, key);	
      }  
    }
}

template<typename K> inline  typename Tree234<K>::Node234 *Tree234<K>::getNextChild(Node234 *current, K key)
{
  int i = 0;
  
  for(; i < current->totalItems; i++) {        

     if (key < current->keys[i]) {

           return current->children[i];  
     }
  }

  return current->children[i];   
}

template<typename K> template<typename Functor> inline void Tree234<K>::traverse(Functor f)
{     
  DoTraverse(f, root);    
}
plate<typename K> template<typename Functor> void Tree234<K>::DoTraverse(Functor f, Node234 *current)
{     
   if (current == nullptr) {

	return;
   }

   switch (current->totalItems) {

      case 1: // two node
            DoTraverse(f, current->children[0]);

            f(current->keys[0]);

            DoTraverse(f, current->children[1]);
            break;

      case 2: // three node
            DoTraverse(f, current->children[0]);

            f(current->keys[0]);

            DoTraverse(f, current->children[1]);
 
            f(current->keys[1]);

            DoTraverse(f, current->children[2]);
            break;

      case 3: // four node
            DoTraverse(f, current->children[0]);

            f(current->keys[0]);

            DoTraverse(f, current->children[1]);
 
            f(current->keys[1]);

            DoTraverse(f, current->children[2]);

            f(current->keys[2]);

            DoTraverse(f, current->children[3]);
 
            break;
   }
}

template<typename K> void Tree234<K>::insert(K key)
{
    if (root == nullptr) {

       root =  new Node234(key); 
       return; 
    } 

   Node234 *current = root;

   /* Descend until a leaf node is found, splitting four nodes as they are encountered */

   while(true) {
       
       if(current->isFull()) {// if four node, split it, moving a value up to parent.

            split(current); 
      
            current = current->getParent(); 

            current = getNextChild(current, key);

       }  else if( current->isLeaf() )  {

            break;

        } else { 

            
                              
            current = getNextChild(current, key);
        }
    }

    // current is now a leaf and not full.
    current->insertItem(key); 
} 

template<typename K> void Tree234<K>::split(Node234 *node)
{
    K  itemB, itemC;

    Node234 *parent, *child2, *child3;

    int itemIndex;

    // remove two largest (of three total) keys...
        
    itemC = node->keys[2];
    itemB = node->keys[1]; 
    node->totalItems = 1; // ...by setting totalItems to 1. 

    // Remove two right-most children from this node. 
    child2 = node->children[2]; 
    child3 = node->children[3]; 

    Node234 *newRight = new Node234(itemC); // make new right child node from largest item


    if (child2 && child3) { // patch: that is, if they are not zero
        
        newRight->connectChild(0, child2); // connect to 0 and 1

        newRight->connectChild(1, child3); // on newRight
    }


    node->children[2] = nullptr; 
    node->children[3] = nullptr; 


    // if this is the root,
    if(node == root) { 
        /* make new root two node using node's middle value */  
        root = new Node234(itemB); 
        parent = root;          // root is parent of node
        root->connectChild(0, node); // connect node to root as left child
        root->connectChild(1, newRight);
        return;

    }         

    parent = node->getParent(); 
    bool bParentWas2Node = parent->totalItems == 1;

    // deal with parent, moving itemB middle value to parent.

    int insert_index = parent->insertItem(itemB);
    int last_index = parent->totalItems - 1;
    
    for(int i = last_index; i > insert_index; i--)  {// move parent's connections right, start from new last index up to insert_index

        Node234 *temp = parent->children[i];  // one child
        parent->connectChild(i + 1, temp);       // to the right
    }

    parent->connectChild(insert_index + 1,  newRight);

  
    return;
}

template<typename K> bool Tree234<K>::remove(K key, Node234 *location)
{
  return true;
}
#endif

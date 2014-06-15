#ifndef TREE234_H
#define   TREE234_H

#include <utility>
#include <iostream>
#include <exception>

template<typename T> class Tree234;
template<typename K> class Node234;

class duplicatekey :  public std::exception {
public:

  virtual const char* what() const throw()
  {
    return "Attempting to insert duplicate key ";
  }
};

template<typename K> class Node234 {
   private:
       friend class Tree234<K>;
       static int MAX;
       Node234(K small);
       Node234(K small, K larger);
       Node234(K small, K middle, K large);

       Node234<K> *parent;

       int totalItems; 
       K keys[3];
       Node234<K> *children[4];

       bool find(K key, int& index);
       int insertItem(K key);
       bool isFull();
       Node234<K> *getParent();
       bool isLeaf();
       void connectChild(int childNum, Node234<K> *child);
};

template<typename K> int Node234<K>::MAX = 3;
template<typename K> inline Node234<K>::Node234(K small) : totalItems(1)
{
   keys[0] = small;
   for (int i = 0; i < MAX; i++) {
       children[i] = 0;
    }
}

template<typename K> inline Node234<K>::Node234(K small, K middle) : totalItems(2)
{
   keys[0] = small;
   keys[1] = middle;
   for (int i = 0; i < MAX; i++) {
       children[i] = 0;
    }
}
template<typename K> inline Node234<K>::Node234(K small, K middle, K large) : totalItems(3)
{
   keys[0] = small;
   keys[1] = middle;
   keys[3] = large;

   for (int i = 0; i < MAX; i++) {
       children[i] = 0;
    }
}

template<typename K> inline void Node234<K>::connectChild(int childNum, Node234<K> *child)
{
  children[childNum] = child;
  child->parent = this;
}

template<typename K> inline int Node234<K>::insertItem(K key)
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



    keys[0] = key;
  ++totalItems;
    return 0;

}

template<typename K> inline  bool Node234<K>::isFull()
{
   return totalItems == MAX;
}

template<typename K> inline  Node234<K> *Node234<K>::getParent()
{
   return parent;
}
template<typename K> inline  bool Node234<K>::isLeaf()
{
   return !children[0] ? true : false;
}

template<typename K> inline bool Node234<K>::find(K key, int& index)
{
   for(int i =0; i < totalItems; i++) {

       if (keys[i] == key) {

            index = i;
            return true;
       }
   }
   return false;
}

template<typename K> class Tree234 {

  protected:

    Node234<K> *root;

    bool DoSearch(K key, Node234<K> *&location, int& index);
    template<typename Functor> void DoTraverse(Functor f, Node234<K> *root);
    Node234<K> *getNextChild(Node234<K> *current, K key);
    void split(Node234<K> *node);
    void DestroyTree(Node234<K> *root);
  public:
     Tree234() { root = 0; }
     ~Tree234();
     bool search(K key);
     bool remove(K key, Node234<K> *location=0);
     template<typename Functor> void traverse(Functor f);
     void insert(K key); // throw(duplicatekey)
};

template<typename K> inline Tree234<K>::~Tree234()
{
   DestroyTree(root);
}


template<typename K> void Tree234<K>::DestroyTree(Node234<K> *current)
{
  if (current == 0) {

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
    if (root == 0) {
       printf("Tree has not any element"); //False print
       return false;

    } else {
        int index;
        Node234<K> *location;
        return DoSearch(key, location, index);
    }
}

template<typename K>  bool Tree234<K>::DoSearch(K key, Node234<K> *&location, int& index)
{
  Node234<K> *current = root;
  if (root == 0) {
     return false;
  }

  while(true) {
      if (current->find(key, index)) {
          location = current;
          return true;

      } else if (current->isLeaf()) {

          return false;

      } else {

          current = getNextChild(current, key);
      }
    }
}

template<typename K> inline Node234<K> *Tree234<K>::getNextChild(Node234<K> *current, K key)
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
template<typename K> template<typename Functor> void Tree234<K>::DoTraverse(Functor f, Node234<K> *current)
{
   if (current == 0) {
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
    if (root == 0) {

       root =  new Node234<K>(key);
       return;
    }

   Node234<K> *current = root;

   while(true) {

       if(current->isFull()) {

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

template<typename K> void Tree234<K>::split(Node234<K> *node)
{
    K  itemB, itemC;

    Node234<K> *parent, *child2, *child3;

    int itemIndex;

    // remove two largest (of three total) keys...

    itemC = node->keys[2];
    itemB = node->keys[1];
    node->totalItems = 1; // ...by setting totalItems to 1.

    // Remove two right-most children from this node.
    child2 = node->children[2];
    child3 = node->children[3];

    Node234<K> *newRight = new Node234<K>(itemC); // make new right child node from largest item

    if (child2 && child3) { // that is, if they are not zero

        newRight->connectChild(0, child2); 

        newRight->connectChild(1, child3); 
    }

    node->children[2] = 0;
    node->children[3] = 0;

    if(node == root) {
        root = new Node234<K>(itemB);
        parent = root;          
        root->connectChild(0, node); 
        root->connectChild(1, newRight);
        return;
    }

    parent = node->getParent();
    bool bParentWas2Node = parent->totalItems == 1;

    

    int insert_index = parent->insertItem(itemB);
    int last_index = parent->totalItems - 1;

    for(int i = last_index; i > insert_index; i--)  {

        Node234<K> *temp = parent->children[i];  
        parent->connectChild(i + 1, temp);       
    }

    parent->connectChild(insert_index + 1,  newRight);

    return;
}

template<typename K> bool Tree234<K>::remove(K key, Node234<K> *location)
{
  return true;
}
#endif


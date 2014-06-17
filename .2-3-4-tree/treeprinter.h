#ifndef TREEPRINTER_H
#define	TREEPRINTER_H

#include <iosfwd>

class TreePrinter {
    
    std::ostream& coutref;
    
public:
    TreePrinter(std::ostream& ostr) : coutref(ostr) {}
     TreePrinter(const TreePrinter& tp) : coutref(tp.coutref) {}
    template<class K> std::ostream& operator()(K k);
};

template<class K> inline std::ostream& TreePrinter::operator()(K k)
{
    coutref << k << ' ';
    return coutref;
}

#endif

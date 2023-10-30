package com.etiya.campus.aj.domain.uygulama1;

import java.util.List;

/*
Sınıfın amacı kullanacağımız değişikenle vb.

 */
public class ParentChildsHolder<P extends DomainItem, C extends DomainItem> {
    /*
    Ana nesne altında da alt nesnelerini tutsun
     */
    /*
    Ana nesnedir....
     */
    private P parent;
    private List<C> childs;

    public void addChild(C child){
        this.childs.add(child);
    }

    public boolean isApplicable(ApplicableState state){
        return this.parent.isApplicable(state);
    }

    public P getParent() {
        return parent;
    }

    public void setParent(P parent) {
        this.parent = parent;
    }

    public List<C> getChilds() {
        return childs;
    }

    public void setChilds(List<C> childs) {
        this.childs = childs;
    }
}

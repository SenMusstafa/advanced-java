package com.etiya.campus.aj.common.container;

import com.etiya.campus.aj.common.provider.IdProvider;
import com.etiya.campus.aj.common.provider.RelationProvider;
import com.etiya.campus.aj.domain.common.Definitions;
import com.etiya.campus.aj.generics.KeyValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class DomainObjectWrapper {
    private String id;
    private String type;
    private Map<String,String> properties;
    private List<DomainObjectWrapper> childObjects;
    private List<KeyValuePair<String,String>> propertiesAsPairs;
    private DomainObjectWrapper parent;
    private boolean lazyloadChilds;
    private boolean treeItem;

    private ForkJoinPool pool;

    public void setPool(ForkJoinPool pool) {
        this.pool = pool;
    }

    public DomainObjectWrapper(Map<String, String> properties) {
        super();
        this.properties = properties;
        this.childObjects = new ArrayList<DomainObjectWrapper>();
        this.propertiesAsPairs = new ArrayList<KeyValuePair<String,String>>();
        this.treeItem = true;
    }

    public DomainObjectWrapper(Map<String, String> properties, String type, String id) {
        super();
        this.properties = properties;
        this.childObjects = new ArrayList<DomainObjectWrapper>();
        this.type = type;
        this.propertiesAsPairs = new ArrayList<KeyValuePair<String,String>>();
        this.id = id;
        this.treeItem = true;
    }

    public void loadSubItems(){
        if((this.getChildObjects()==null)||(this.getChildObjects().size()<=0)){
            List<String> relations = RelationProvider.get(this.type);
            DomainObjectWrapper child = null;
            List<DomainObjectWrapper> childList = null;
            if(relations!=null){
                for(String rel : relations){
                    ApplicationService applicationService  = new ApplicationService();
                    String relType = RelationProvider.geRelType(this.type,rel);
                    if(relType!=null){
                        if(relType.equals(Definitions.RelType.ONETOONE.toString())){
                            child = applicationService.searchByType(this.properties.get(IdProvider.get(this.type, rel)), rel, this.type);
                            if(child!=null){
                                child.setParent(this);
                                child.setLazyloadChilds(this.isLazyloadChilds());
                                this.addToChilds(child);
                                if(!lazyloadChilds)
                                    child.loadSubItems();
                            }
                        }
                        else
                        if(relType.equals(Definitions.RelType.ONETOMANY.toString())){
                            childList = applicationService.searchListByType(this.properties.get(IdProvider.get(this.type, rel)), rel);
                            if((childList!=null)&&(childList.size()>0)){
                                for(DomainObjectWrapper childObject : childList){
                                    this.addToChilds(childObject);
                                    childObject.setParent(this);
                                    childObject.setLazyloadChilds(this.isLazyloadChilds());
                                    if(!lazyloadChilds){
                                        childObject.loadSubItems();
                                    }
                                }
                            }
                        }
                        else
                        if(relType.equals(Definitions.RelType.ABSTRACT.toString())){
                            child = new DomainObjectWrapper(this.properties, rel, this.id);
                            child.setParent(this);
                            child.setLazyloadChilds(this.isLazyloadChilds());
                            this.addToChilds(child);
                            if(!lazyloadChilds)
                                child.loadSubItems();
                        }
                    }
                }
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DomainObjectWrapper other = (DomainObjectWrapper) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    private void addToChilds(DomainObjectWrapper child){
        if((child!=null)&&(!this.existInParents(child))){
            this.childObjects.add(child);
        }
    }

    public boolean existInParents(DomainObjectWrapper child){
        boolean result = false;
        if(this.parent!=null){
            result = this.equals(child) || this.parent.existInParents(child);
        }
        return result;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public List<DomainObjectWrapper> getChildObjects() {
        return childObjects;
    }

    public void setChildObjects(List<DomainObjectWrapper> childObjects) {
        this.childObjects = childObjects;
    }

    public List<KeyValuePair<String, String>> getPropertiesAsPairs() {
        return propertiesAsPairs;
    }

    public void setPropertiesAsPairs(List<KeyValuePair<String, String>> propertiesAsPairs) {
        this.propertiesAsPairs = propertiesAsPairs;
    }

    public DomainObjectWrapper getParent() {
        return parent;
    }

    public void setParent(DomainObjectWrapper parent) {
        this.parent = parent;
    }

    public boolean isLazyloadChilds() {
        return lazyloadChilds;
    }

    public void setLazyloadChilds(boolean lazyloadChilds) {
        this.lazyloadChilds = lazyloadChilds;
    }

    public boolean isTreeItem() {
        return treeItem;
    }

    public void setTreeItem(boolean treeItem) {
        this.treeItem = treeItem;
    }
}

package com.etiya.campus.aj.common.container;

import com.etiya.campus.aj.common.container.LinkedElement;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LinkedElementContainer<T> {
    private LinkedList<LinkedElement<T>> elements;

    public LinkedElementContainer(){
        elements = new LinkedList<>();
        //elements.add(s)
        //elements.add(i,w)
    }

    public void addElement(LinkedElement<T> element){

    }

    public void addElement(T element){

    }

    public void addAll(Collection<T> elements){

    }

    public List<T> getElementTypeList(){
        return this.elements.stream().map(e->e.getElement()).collect(Collectors.toList());
    }
}

package com.etiya.campus.aj.common.container;

import java.util.Objects;

public class LinkedElement<T> {
    private T element;
    private T next;
    private T previous;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public T getNext() {
        return next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedElement<?> that = (LinkedElement<?>) o;
        return Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }

    public void setNext(T next) {
        this.next = next;
    }

    public T getPrevious() {
        return previous;
    }

    public void setPrevious(T previous) {
        this.previous = previous;
    }
}

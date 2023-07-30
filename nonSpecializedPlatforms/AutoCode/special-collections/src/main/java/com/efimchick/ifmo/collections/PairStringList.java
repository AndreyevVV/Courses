package com.efimchick.ifmo.collections;

import java.util.ArrayList;
import java.util.Collection;

class PairStringList<E> extends ArrayList<E> {

    public PairStringList() {
        super ();
    }
    @Override
    public boolean add (E s) {
        super.add(s);
        super.add(s);
        return true;
    }

    @Override
    public E get(int index) {return super.get(index);}

    @Override
    public E set(int index, E element) {
        if (index % 2 == 0){
            super.set(index, element);
            return super.set(index+1, element);
        } else {
            super.set(index-1, element);
            return super.set(index, element);
        }
    }

    @Override
    public void add(int index, E element) {
        if (index % 2 == 0){
            super.add(index, element);
            super.add(index, element);
        } else {
            super.add(index+1, element);
            super.add(index+1, element);
        }
    }

    @Override
    public E remove(int index) {
        if (index % 2 == 0){
            super.remove(index);
            super.remove(index+1);
        } else {
            super.remove(index);
            super.remove(index-1);
        }
        return null;
    }

    @Override
    public boolean remove(Object s) {
        int index = super.indexOf(s);
        super.remove(index);
        super.remove(index);
        return true;
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element:c){
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int i = 0;
        for (E element: c){
            add(index + 2*i++, element);
        }
        return true;
    }
}

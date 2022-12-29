package com.javaTraining.nobug.vo;

import com.javaTraining.nobug.common.ResultObj;
import com.javaTraining.nobug.vo.abstractVo.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Composite extends Component {

    private List<Component> mylist = new List<Component>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Component> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Component component) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Component> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Component> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public Component get(int index) {
            return null;
        }

        @Override
        public Component set(int index, Component element) {
            return null;
        }

        @Override
        public void add(int index, Component element) {

        }

        @Override
        public Component remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Component> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Component> listIterator(int index) {
            return null;
        }

        @Override
        public List<Component> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    @Override
    public ResultObj operation() {
        return null;
    }

    @Override
    public Boolean add(Component component) {
        try{
            mylist.add(component);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public Component remove() {
        if(mylist.size()>0){
            Component component = mylist.get(mylist.size()-1);
            mylist.remove(mylist.size() - 1);
            return component;
        }else {
            return null;
        }
    }

    @Override
    public List<Component> getChild() {
        return mylist;
    }
}

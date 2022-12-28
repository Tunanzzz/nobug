package com.javaTraining.nobug.service.factory.core;

public interface FactoryService<T extends FactoryType> {
    T supportType();
}

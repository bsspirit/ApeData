package org.ape.data.core.task;

/**
 * 
 * @author Conan_Z
 * @data 2012-12-19
 */
public interface Task {

    void createTask();
    void getTask(int id);
    void startTask(int id);
    void stopTask(int id);
    
}

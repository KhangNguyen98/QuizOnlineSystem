/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.validators;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author khang nguyen
 */
public abstract class Validator<T> {
    private Map<String, String> error;
    T object;
    public Validator(T object)
    {
        error = new HashMap<String, String>();
        this.object = object;
    }
    public boolean hasError()
    {
        return !error.isEmpty();
    }
    public Map<String, String> getError()
    {
        return error;
    }
    public void addError(String errorCode, String message)
    {
        error.put(errorCode, message);
    }
    public abstract void validate();
}

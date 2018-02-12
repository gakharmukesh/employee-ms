package com.mukesh.ms.lab.employee.services.interfaces;

public interface IMessages 
{

	 /**
     * Returns the localized message for the given key.
     * 
     * @param key
     * @param params
     * @return
     */
    String getMessage(String key, String... params);
}

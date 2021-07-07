package com.saral.accountService.services;

public abstract class ResponseService <T> {

    public T handleResponse(){

        try{
            return handle();
        } catch (Exception ex){
            handleException(ex);
        } finally {

        }
        return null;
    }

    public abstract T handle() throws Exception;
    public abstract T handleException(Exception ex);
}

package com.saral.keycloak.client.services;

public abstract class ResponseService<R, T> {

    public T handleResponse(R rq){

        try{
            return handle(rq);
        } catch (Exception ex){
            handleException(ex);
        } finally {
            handleFinally();
        }
        return null;
    }

    public abstract T handle(R rq) throws Exception;
    public abstract T handleException(Exception ex);

    public void handleFinally(){
        // If required Override this method and implemented your changes.
    }
}

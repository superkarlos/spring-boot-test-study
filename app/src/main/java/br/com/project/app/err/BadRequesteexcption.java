package br.com.project.app.err;

public class BadRequesteexcption extends RuntimeException {
    
    public BadRequesteexcption(){

    }

     public BadRequesteexcption(String mString){
        super(mString);
    }
}

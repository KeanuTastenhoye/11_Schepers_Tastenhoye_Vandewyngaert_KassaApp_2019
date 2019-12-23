package domain;

/**
 * //@author Phloy
 */
    public class DomainException extends Throwable {
        private Exception ex;
        private String str;
        public DomainException(String str, Exception ex){

            this.str=str;
            this.ex=ex;
        }
    }


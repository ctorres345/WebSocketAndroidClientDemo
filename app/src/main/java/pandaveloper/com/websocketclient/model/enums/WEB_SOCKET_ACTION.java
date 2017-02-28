package pandaveloper.com.websocketclient.model.enums;

/**
 * Created by cesar on 1/31/17.
 */

public enum WEB_SOCKET_ACTION {
    ADD {
        @Override
        public String getDescription() {
            return "add object";
        }

        @Override
        public long getCode() {
            return 1;
        }
    },
    REMOVE{
        @Override
        public String getDescription() {
            return "remove the object";
        }

        @Override
        public long getCode() {
            return 2;
        }
    },
    TOGGLE {
        @Override
        public String getDescription() {
            return "toggle the object state";
        }

        @Override
        public long getCode() {
            return 3;
        }
    },
    GET {
        @Override
        public String getDescription() {
            return "get object list";
        }

        @Override
        public long getCode() {
            return 4;
        }
    };

    public abstract String getDescription();

    public abstract long getCode();

}

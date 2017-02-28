package pandaveloper.com.websocketclient.model.enums;

/**
 * Created by cesar on 1/31/17.
 */

public enum UNIT_CLASS_TYPE {

    KNIGHT {
        @Override
        public String getDescription() {
            return "Knight";
        }

        @Override
        public long getCode() {
            return 1;
        }


    },
    LANCER{
        @Override
        public String getDescription() {
            return "Lancer";
        }

        @Override
        public long getCode() {
            return 2;
        }
    },
    ARCHER {
        @Override
        public String getDescription() {
            return "Archer";
        }

        @Override
        public long getCode() {
            return 3;
        }
    },
    LORD {
        @Override
        public String getDescription() {
            return "Lord";
        }

        @Override
        public long getCode() {
            return 4;
        }
    };

    public abstract String getDescription();
    public abstract long getCode();
}

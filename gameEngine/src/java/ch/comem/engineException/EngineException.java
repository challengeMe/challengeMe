package ch.comem.engineException;
/**
 * class qui permet de générer les exceptions de l'engine
 * @author bastieneichenberger
 */
public class EngineException extends RuntimeException {

    private StatutsCode statut;

    public StatutsCode getStatut() {
        return statut;
    }

    public void setStatut(StatutsCode statut) {
        this.statut = statut;
    }

    public EngineException(StatutsCode statut) {
        super();
        this.statut = statut;
    }

    public EngineException(String message, StatutsCode statut) {
        super(message);
        this.statut = statut;
    }

    public EngineException(Throwable cause, StatutsCode statut) {
        super(cause);
        this.statut = statut;
    }

    public EngineException(String message, Throwable cause, StatutsCode statut) {
        super(message, cause);
        this.statut = statut;
    }

    public enum StatutsCode {

        PLAYER_NOT_FOUND,
        BADGE_NOT_FOUND;
    }
}

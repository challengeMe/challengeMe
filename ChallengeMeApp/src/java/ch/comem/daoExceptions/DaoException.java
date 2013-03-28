package ch.comem.daoExceptions;

public class DaoException extends RuntimeException {

    private StatutsCode statut;

    public StatutsCode getStatut() {
        return statut;
    }

    public void setStatut(StatutsCode statut) {
        this.statut = statut;
    }

    public DaoException(StatutsCode statut) {
        super();
        this.statut = statut;
    }

    public DaoException(String message, StatutsCode statut) {
        super(message);
        this.statut = statut;
    }

    public DaoException(Throwable cause, StatutsCode statut) {
        super(cause);
        this.statut = statut;
    }

    public DaoException(String message, Throwable cause, StatutsCode statut) {
        super(message, cause);
        this.statut = statut;
    }

    public enum StatutsCode {

        GROUPE_NOT_FOUND,
        MEMBRE_NOT_FOUND,
        MISSION_NOT_FOUND,
        PHOTO_NOT_FOUND,
        VIDEO_NOT_FOUND,
        MEDIA_NOT_FOUND,
        CI_NOT_RESPECTED;
    }
}

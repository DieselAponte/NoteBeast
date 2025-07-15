package aponte.dev.notebeast.model;

public class UserConfig {
    //Atributos de configuración para la interfaz
    private String theme;
    private int fontSize;
    private String language;
    //Atributos de configuración para el comportamiento de la aplicación
    private boolean notifications;
    //Atributos de configuración para el Timer
    private int defaultStudyDuration;
    private int defaultBreakDuration;
    private int alertDaysInAdvance;
    //Atributos de configuración para el bloqueo(PIN)
    private int blockPIN;

    public UserConfig(String theme, int fontSize, String language, boolean notifications, int defaultStudyDuration,
                      int defaultBreakDuration, int alertDaysInAdvance, int blockPIN) {
        this.theme = theme;
        this.fontSize = fontSize;
        this.language = language;
        this.notifications = notifications;
        this.defaultStudyDuration = defaultStudyDuration;
        this.defaultBreakDuration = defaultBreakDuration;
        this.alertDaysInAdvance = alertDaysInAdvance;
        this.blockPIN = blockPIN;
    }

    //Getters
    public String getTheme() {
        return theme;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public int getDefaultStudyDuration() {
        return defaultStudyDuration;
    }

    public int getDefaultBreakDuration() {
        return defaultBreakDuration;
    }

    public int getAlertDaysInAdvance() {
        return alertDaysInAdvance;
    }

    public int getBlockPIN() {
        return blockPIN;
    }

    //Setters
    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public void setDefaultStudyDuration(int defaultStudyDuration) {
        this.defaultStudyDuration = defaultStudyDuration;
    }

    public void setDefaultBreakDuration(int defaultBreakDuration) {
        this.defaultBreakDuration = defaultBreakDuration;
    }

    public void setAlertDaysInAdvance(int alertDaysInAdvance) {
        this.alertDaysInAdvance = alertDaysInAdvance;
    }

    public void setBlockPIN(int blockPIN) {
        this.blockPIN = blockPIN;
    }
}

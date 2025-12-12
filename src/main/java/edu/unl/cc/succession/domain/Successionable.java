package edu.unl.cc.succession.domain;

/**
 *  @autor Xander (Alexander Gallo)
 */
public interface Successionable extends Printable {
    /**
     * Generate the next term in the series based on the current term.
     * @param currentTerm The current term in the series
     * @return The next term in the series
     */
    Number nextTerm (Number currentTerm);

    /**
     * Setter the limit in the series
     * @param limit
     */
    void setLimit (Number limit);

    /**
     * Calculate the result, if the necessary in the serie. The main method
     * @return the result of the calaculate
     */
    Number calculate ();

}

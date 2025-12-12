package edu.unl.cc.succession.domain;

/**
 *  @autor Grupo2 (PIA)
 *   - Integrantes:
 *      - Francisco Chamba
 *      - Alexander Gallo
 *      - Franz Ludeña
 *      - Jeam Romero
 */
public interface Successionable extends Printable {

    void setLimit(Number limite);

    Number calculate();

    Number nextTerm(Number currentTerm);

}

package br.unp.five.models;

import br.unp.five.models.Area;
import br.unp.five.models.Classificacao;
import br.unp.five.models.Peso;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-17T16:06:34")
@StaticMetamodel(Periodicos.class)
public class Periodicos_ { 

    public static volatile SingularAttribute<Periodicos, Classificacao> idClassificacao;
    public static volatile SingularAttribute<Periodicos, String> tipo;
    public static volatile SingularAttribute<Periodicos, String> issn;
    public static volatile SingularAttribute<Periodicos, Area> idArea;
    public static volatile SingularAttribute<Periodicos, Date> inicioprazo;
    public static volatile SingularAttribute<Periodicos, String> titulo;
    public static volatile SingularAttribute<Periodicos, Integer> idPeriodico;
    public static volatile SingularAttribute<Periodicos, Date> fimprazo;
    public static volatile SingularAttribute<Periodicos, String> pais;
    public static volatile SingularAttribute<Periodicos, String> status;
    public static volatile SingularAttribute<Periodicos, Peso> idPeso;

}
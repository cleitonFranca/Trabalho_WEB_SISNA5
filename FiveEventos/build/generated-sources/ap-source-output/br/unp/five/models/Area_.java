package br.unp.five.models;

import br.unp.five.models.Classificacao;
import br.unp.five.models.Periodicos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-17T16:06:34")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile ListAttribute<Area, Periodicos> periodicosList;
    public static volatile ListAttribute<Area, Classificacao> classificacaoList;
    public static volatile SingularAttribute<Area, Integer> idArea;
    public static volatile SingularAttribute<Area, String> descricao;

}
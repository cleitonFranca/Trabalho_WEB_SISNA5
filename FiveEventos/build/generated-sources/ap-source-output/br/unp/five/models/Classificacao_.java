package br.unp.five.models;

import br.unp.five.models.Area;
import br.unp.five.models.Periodicos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-17T16:06:34")
@StaticMetamodel(Classificacao.class)
public class Classificacao_ { 

    public static volatile ListAttribute<Classificacao, Periodicos> periodicosList;
    public static volatile SingularAttribute<Classificacao, Integer> idClassificacao;
    public static volatile ListAttribute<Classificacao, Area> areaList;
    public static volatile SingularAttribute<Classificacao, String> descricao;

}
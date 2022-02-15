package com.lyra.mail.product.entity.vo;

import java.util.List;

public class Catalog2VO {
    private String catalog1Id;
    private String id;
    private String name;

    private List<Catalog3> catalog3List;




    public static class Catalog3 {
        private String catalog2Id;
        private String id;
        private String name;

        public Catalog3(String catalog2Id, String id, String name) {
            this.catalog2Id = catalog2Id;
            this.id = id;
            this.name = name;
        }

        public Catalog3() {
        }

        public String getCatalog2Id() {
            return catalog2Id;
        }

        public void setCatalog2Id(String catalog2Id) {
            this.catalog2Id = catalog2Id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getCatalog1Id() {
        return catalog1Id;
    }

    public void setCatalog1Id(String catalog1Id) {
        this.catalog1Id = catalog1Id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Catalog3> getCatalog3List() {
        return catalog3List;
    }

    public void setCatalog3List(List<Catalog3> catalog3List) {
        this.catalog3List = catalog3List;
    }
}

package com.example.administrator.jsondemo2;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */

public class NewsType {
    private List<TypeData> data;
    public NewsType(List<TypeData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewsType{" +
                "data=" + data +
                '}';
    }

    public List<TypeData> getData() {
        return data;
    }

    public void setData(List<TypeData> data) {
        this.data = data;
    }

    public static class TypeData {
        @Override
        public String toString() {
            return "TypeData{" +
                    "gid=" + gid +
                    ", group='" + group + '\'' +
                    ", subgrp=" + subgrp +
                    '}';
        }

        private int gid;//属性类型对应 --json中的键值对中 值的类型
        // 属性名---对应  json键名
        private String group;
        private List<SubTypeData> subgrp;

        public TypeData(List<SubTypeData> subgrp, int gid, String group) {
            this.subgrp = subgrp;
            this.gid = gid;
            this.group = group;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public List<SubTypeData> getSubgrp() {
            return subgrp;
        }

        public void setSubgrp(List<SubTypeData> subgrp) {
            this.subgrp = subgrp;
        }
    }


    public static class SubTypeData {
        private String subgroup;
        private int subid;

        @Override
        public String toString() {
            return "SubTypeData{" +
                    "subgroup='" + subgroup + '\'' +
                    ", subid=" + subid +
                    '}';
        }

        public SubTypeData(String subgroup, int subid) {
            this.subgroup = subgroup;
            this.subid = subid;
        }

        public String getSubgroup() {
            return subgroup;
        }

        public void setSubgroup(String subgroup) {
            this.subgroup = subgroup;
        }

        public int getSubid() {
            return subid;
        }

        public void setSubid(int subid) {
            this.subid = subid;
        }
    }
}

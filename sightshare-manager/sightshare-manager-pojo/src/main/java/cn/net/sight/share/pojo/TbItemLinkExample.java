package cn.net.sight.share.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbItemLinkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbItemLinkExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Long value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Long value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Long value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Long value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Long value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Long> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Long> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Long value1, Long value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Long value1, Long value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemTitleIsNull() {
            addCriterion("item_title is null");
            return (Criteria) this;
        }

        public Criteria andItemTitleIsNotNull() {
            addCriterion("item_title is not null");
            return (Criteria) this;
        }

        public Criteria andItemTitleEqualTo(String value) {
            addCriterion("item_title =", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotEqualTo(String value) {
            addCriterion("item_title <>", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleGreaterThan(String value) {
            addCriterion("item_title >", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleGreaterThanOrEqualTo(String value) {
            addCriterion("item_title >=", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLessThan(String value) {
            addCriterion("item_title <", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLessThanOrEqualTo(String value) {
            addCriterion("item_title <=", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLike(String value) {
            addCriterion("item_title like", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotLike(String value) {
            addCriterion("item_title not like", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleIn(List<String> values) {
            addCriterion("item_title in", values, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotIn(List<String> values) {
            addCriterion("item_title not in", values, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleBetween(String value1, String value2) {
            addCriterion("item_title between", value1, value2, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotBetween(String value1, String value2) {
            addCriterion("item_title not between", value1, value2, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemLinkIsNull() {
            addCriterion("item_link is null");
            return (Criteria) this;
        }

        public Criteria andItemLinkIsNotNull() {
            addCriterion("item_link is not null");
            return (Criteria) this;
        }

        public Criteria andItemLinkEqualTo(String value) {
            addCriterion("item_link =", value, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkNotEqualTo(String value) {
            addCriterion("item_link <>", value, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkGreaterThan(String value) {
            addCriterion("item_link >", value, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkGreaterThanOrEqualTo(String value) {
            addCriterion("item_link >=", value, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkLessThan(String value) {
            addCriterion("item_link <", value, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkLessThanOrEqualTo(String value) {
            addCriterion("item_link <=", value, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkLike(String value) {
            addCriterion("item_link like", value, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkNotLike(String value) {
            addCriterion("item_link not like", value, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkIn(List<String> values) {
            addCriterion("item_link in", values, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkNotIn(List<String> values) {
            addCriterion("item_link not in", values, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkBetween(String value1, String value2) {
            addCriterion("item_link between", value1, value2, "itemLink");
            return (Criteria) this;
        }

        public Criteria andItemLinkNotBetween(String value1, String value2) {
            addCriterion("item_link not between", value1, value2, "itemLink");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordIsNull() {
            addCriterion("link_password is null");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordIsNotNull() {
            addCriterion("link_password is not null");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordEqualTo(String value) {
            addCriterion("link_password =", value, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordNotEqualTo(String value) {
            addCriterion("link_password <>", value, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordGreaterThan(String value) {
            addCriterion("link_password >", value, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("link_password >=", value, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordLessThan(String value) {
            addCriterion("link_password <", value, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordLessThanOrEqualTo(String value) {
            addCriterion("link_password <=", value, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordLike(String value) {
            addCriterion("link_password like", value, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordNotLike(String value) {
            addCriterion("link_password not like", value, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordIn(List<String> values) {
            addCriterion("link_password in", values, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordNotIn(List<String> values) {
            addCriterion("link_password not in", values, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordBetween(String value1, String value2) {
            addCriterion("link_password between", value1, value2, "linkPassword");
            return (Criteria) this;
        }

        public Criteria andLinkPasswordNotBetween(String value1, String value2) {
            addCriterion("link_password not between", value1, value2, "linkPassword");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
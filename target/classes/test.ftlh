#ftl encoding="UTF-8"/>
<#-- @ftlvariable name="message" type="java.lang.String" -->
<#import "spring.ftl" as spring />

<@layout.contents article.title>
    <#if message?has_content>
        <h6>${message}</h6>
    <#else>
        <@article_layout.contents article/>
    </#if>
</@layout.contents>
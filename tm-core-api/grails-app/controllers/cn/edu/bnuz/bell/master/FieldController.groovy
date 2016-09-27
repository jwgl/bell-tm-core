package cn.edu.bnuz.bell.master

import grails.converters.JSON

/**
 * 专业目录控制器
 * @author Yang Lin
 */
class FieldController {
    FieldService fieldService

    def index() {
        render fieldService.getFields('2012') as JSON
    }
}

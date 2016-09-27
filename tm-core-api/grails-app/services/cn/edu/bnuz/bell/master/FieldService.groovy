package cn.edu.bnuz.bell.master

/**
 * 专业目录服务
 * @author Yang Lin
 */
class FieldService {
    def getFields(String version) {
        Field.executeQuery '''
select new map(
  f.id as id,
  f.code as code,
  f.name as name,
  f.flag as flag,
  fc.name as fieldClass,
  d.name as discipline
) from Field f
join f.fieldClass fc
join fc.discipline d
where substring(cast(f.id as text), 1, 4) = :version
order by f.id
''', [version: version]
    }
}

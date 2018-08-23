import DS from 'ember-data';

export default DS.Model.extend({
    title: DS.attr('string'),
    body: DS.attr('string'),
    response: DS.attr('string'),
    datereported: DS.attr('date'),
    dateresolved: DS.attr('date'),
    employee: DS.belongsTo('employee',{asynch:true}),
    manager: DS.belongsTo('manager',{asynch:true}),
    client: DS.belongsTo('client',{asynch:true})
});

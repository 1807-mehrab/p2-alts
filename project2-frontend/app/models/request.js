import DS from 'ember-data';

export default DS.Model.extend({
    title: DS.attr('string'),
    price: DS.attr('string'),
    photo: DS.attr('string'),
    isapproved: DS.attr('boolean'),
    client: DS.belongsTo('client', {asynch:true}),
    manager: DS.belongsTo('manager', {asynch:true})
});

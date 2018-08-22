import EmberRouter from '@ember/routing/router';
import config from './config/environment';

const Router = EmberRouter.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('all-games', { path: '/games' });
  this.route('about',{ path: '/about' });
  this.route('main',{ path: '/main' } );
  this.route('profile');
  this.route('sell-game');
  this.route('cart');
});

export default Router;

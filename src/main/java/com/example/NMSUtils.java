package com.example;
 
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;

import me.benfah.cu.util.ReflectionUtils;

 
/**
 * A Free-to-use library class for registering custom entities in Minecraft, using the Spigot server software (ver. 1.12-pre) as its API.
 *
 * @author jetp250
 */
public class NMSUtils{
	
    private static final Field META_LIST_MONSTER;
    private static final Field META_LIST_CREATURE;
    private static final Field META_LIST_WATER_CREATURE;
    private static final Field META_LIST_AMBIENT;
 
 
    public static final Object CRAFTBUKKIT_SERVER;
    public static final Object MINECRAFT_SERVER;
 
    
    
    
    /**
     * Registers an Item (Not an ItemStack!) to be available for use. an ItemStack can then be created using <code>new ItemStack(item)</code>.
     *
     * @param name
     *            - The name of the item, can be anything
     * @param id
     *            - The ID of the item, will be rendered depending on this
     * @param item
     *            - The net.minecraft.server.version.Item itself
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws NoSuchFieldException 
     */
    public static void registerItem(final String name, final int id, final Object item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, NoSuchFieldException {
    	Class<?> itemClass = ReflectionUtils.getRefClass("{nms}.Item");
    	Class<?> mkClass = ReflectionUtils.getRefClass("{nms}.MinecraftKey");
    	Object reg = itemClass.getField("REGISTRY").get(null);
        reg.getClass().getMethod("a", int.class, mkClass, itemClass).invoke(reg, id, mkClass.getConstructor(String.class).newInstance(name), item);
    }
 
    /**
     * Adds a random spawn for the mob with the specified arguments.
     * <p>
     * If you're using a custom entity class, remember to <b>register</b> it before using this! Otherwise it'll not be rendered by the client.
     * <p>
     * If {@link #isAccessible()} returns false, the process will not be executed.
     *
     * @see #registerEntity(MobType, Class, boolean)
     * @param type
     *            - The mob type to spawn
     * @param data
     *            - The spawn data (chance, amount, spawn weight..)
     * @param biomes
     *            - The array of biomes to let the mobs spawn in, use {@link Biome#COLLECTION_ALL} for all of them.
     */
    
 
    /**
     * Registers the custom class to be available for use.
     *
     * @param type
     *            - The type of your mob
     * @param customClass
     *            - Your custom class that'll be used
     * @param biomes
     *            - Should your mob be set as a default in each biome? Only one custom entity of this type entity can have this set as 'true'.
     */
    
 
    /**
     * Registers the custom class to be available for use.
     *
     * @param id
     *            - The mob id. BE CAREFUL with this. Your Minecraft client renders the entity based on this, and if used improperly, will cause
     *            unexpected behavior!
     * @param name
     *            - The 'savegame id' of the mob.
     * @param type
     *            - The type of your mob
     * @param customClass
     *            - Your custom class that'll be used
     * @param biomes
     *            - The array of biomes to make the mob spawn in.
     * @see #registerEntity(int, String, MobType, Class, Biome[])
     * @see EntityType#getName() EntityType#getName() for the savegame id.
     * @see EntityType#getId() EntityType#getId() for the correct mob id.
     */
    
    
    public static void registerEntity(final String name, final Type type, final Class<?> customClass,
            boolean biomes) {
    	
    	Class mkClass = ReflectionUtils.getRefClass("{nms}.MinecraftKey");
    	Class etClass = ReflectionUtils.getRefClass("{nms}.EntityTypes");
    	
		try {
			Object key = mkClass.getConstructor(String.class).newInstance(name);
		

        Object etb = etClass.getField("b").get(null);
        etb.getClass().getMethod("a", int.class, Object.class, Object.class).invoke(etb, type.getId(), key, customClass);
        
        if (!((Set) etClass.getField("d").get(null)).contains(key)) {
        	((Set) etClass.getField("d").get(null)).add(key);
        }
        if (!biomes || type.isSpecial()) {
            return;
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
 
    /**
     * An enum containing all the biomes of Minecraft. The descriptions are taken from the <a href="http://minecraft.gamepedia.com/Biome">Minecraft
     * Wiki Page</a>, which also has images for each biome.
     *
     */
    
 
    public enum Type {
    	
    	
    	
    	
        DROPPED_ITEM(1, "item", getNMSClass("EntityItem"), MobMeta.UNDEFINED, true),
        EXPERIENCE_ORB(2, "xp_orb", getNMSClass("EntityExperienceOrb"), MobMeta.UNDEFINED, true),
        AREA_EFFECT_CLOUD(3, "area_effect_cloud", getNMSClass("EntityAreaEffectCloud"), MobMeta.UNDEFINED, true),
        ELDER_GUARDIAN(4, "elder_guardian", getNMSClass("EntityGuardianElder"), MobMeta.MONSTER, false),
        WITHER_SKELETON(5, "wither_skeleton", getNMSClass("EntitySkeletonWither"), MobMeta.MONSTER, false),
        STRAY(6, "stray", getNMSClass("EntitySkeletonStray"), MobMeta.MONSTER, false),
        THROWN_EGG(7, "egg", getNMSClass("EntityEgg"), MobMeta.UNDEFINED, true),
        LEAD_KNOT(8, "leash_knot", getNMSClass("EntityLeash"), MobMeta.UNDEFINED, true),
        PAINTING(9, "painting", getNMSClass("EntityPainting"), MobMeta.UNDEFINED, true),
        ARROW(10, "arrow", getNMSClass("EntityArrow"), MobMeta.UNDEFINED, true),
        SNOWBALL(11, "snowball", getNMSClass("EntitySnowball"), MobMeta.UNDEFINED, true),
        FIREBALL(12, "fireball", getNMSClass("EntityFireball"), MobMeta.UNDEFINED, true),
        SMALL_FIREBALL(13, "fireball", getNMSClass("EntitySmallFireball"), MobMeta.UNDEFINED, true),
        ENDER_PEARL(14, "ender_pearl", getNMSClass("EntityEnderPearl"), MobMeta.UNDEFINED, true),
        EYE_OF_ENDER(15, "eye_of_ender_signal", getNMSClass("EntityEnderSignal"), MobMeta.UNDEFINED, true),
        POTION(16, "potion", getNMSClass("EntityPotion"), MobMeta.UNDEFINED, true),
        EXP_BOTTLE(17, "xp_bottle", getNMSClass("EntityThrownExpBottle"), MobMeta.UNDEFINED, true),
        ITEM_FRAME(18, "item_frame", getNMSClass("EntityItemFrame"), MobMeta.UNDEFINED, true),
        WITHER_SKULL(19, "wither_skull", getNMSClass("EntityWitherSkull"), MobMeta.UNDEFINED, true),
        PRIMED_TNT(20, "tnt", getNMSClass("EntityTNTPrimed"), MobMeta.UNDEFINED, true),
        FALLING_BLOCK(21, "falling_block", getNMSClass("EntityFallingBlock"), MobMeta.UNDEFINED, true),
        FIREWORK_ROCKET(22, "fireworks_rocket", getNMSClass("EntityFireworks"), MobMeta.UNDEFINED, true),
        HUSK(23, "husk", getNMSClass("EntityZombieHusk"), MobMeta.MONSTER, false),
        SPECTRAL_ARROW(24, "spectral_arrow", getNMSClass("EntitySpectralArrow"), MobMeta.UNDEFINED, true),
        SHULKER_BULLET(25, "shulker_bullet", getNMSClass("EntityShulkerBullet"), MobMeta.UNDEFINED, true),
        DRAGON_FIREBALL(26, "dragon_fireball", getNMSClass("EntityDragonFireball"), MobMeta.UNDEFINED, true),
        ZOMBIE_VILLAGER(27, "zombie_villager", getNMSClass("EntityZombieVillager"), MobMeta.MONSTER, false),
        SKELETON_HORSE(28, "skeleton_horse", getNMSClass("EntityHorseSkeleton"), MobMeta.CREATURE, false),
        ZOMBIE_HORSE(29, "zombie_horse", getNMSClass("EntityHorseZombie"), MobMeta.CREATURE, false),
        ARMOR_STAND(30, "armor_stand", getNMSClass("EntityArmorStand"), MobMeta.UNDEFINED, true),
        DONKEY(31, "donkey", getNMSClass("EntityHorseDonkey"), MobMeta.CREATURE, false),
        MULE(32, "mule", getNMSClass("EntityHorseMule"), MobMeta.CREATURE, false),
        EVOCATION_FANGS(33, "evocation_fangs", getNMSClass("EntityEvokerFangs"), MobMeta.UNDEFINED, true),
        EVOKER(34, "evocation_illager", getNMSClass("EntityEvoker"), MobMeta.MONSTER, false),
        VEX(35, "vex", getNMSClass("EntityVex"), MobMeta.MONSTER, false),
        VINDICATOR(36, "vindication_illager", getNMSClass("EntityVindicator"), MobMeta.MONSTER, false),
        ILLUSIONER(37, "illusion_illager", getNMSClass("EntityIllagerIllusioner"), MobMeta.MONSTER, false),
        COMMAND_BLOCK_MINECART(40, "commandblock_minecart", getNMSClass("EntityMinecartCommandBlock"), MobMeta.UNDEFINED, true),
        BOAT(41, "boat", getNMSClass("EntityBoat"), MobMeta.UNDEFINED, true),
        MINECART(42, "minecart", getNMSClass("EntityMinecartRideable"), MobMeta.UNDEFINED, true),
        CHEST_MINECART(43, "chest_minecart", getNMSClass("EntityMinecartChest"), MobMeta.UNDEFINED, true),
        FURNACE_MINECART(44, "furnace_minecart", getNMSClass("EntityMinecartFurnace"), MobMeta.UNDEFINED, true),
        TNT_MINECART(45, "tnt_minecart", getNMSClass("EntityMinecartTNT"), MobMeta.UNDEFINED, true),
        HOPPER_MINECART(46, "hopper_minecart", getNMSClass("EntityMinecartHopper"), MobMeta.UNDEFINED, true),
        SPAWNER_MINECART(47, "spawner_minecart", getNMSClass("EntityMinecartMobSpawner"), MobMeta.UNDEFINED, true),
        CREEPER(50, "creeper", getNMSClass("EntityCreeper"), MobMeta.MONSTER, false),
        SKELETON(51, "skeleton", getNMSClass("EntitySkeleton"), MobMeta.MONSTER, false),
        SPIDER(52, "spider", getNMSClass("EntitySpider"), MobMeta.MONSTER, false),
        GIANT(53, "giant", getNMSClass("EntityGiantZombie"), MobMeta.MONSTER, false),
        ZOMBIE(54, "zombie", getNMSClass("EntityZombie"), MobMeta.MONSTER, false),
        SLIME(55, "slime", getNMSClass("EntitySlime"), MobMeta.MONSTER, false),
        GHAST(56, "ghast", getNMSClass("EntityGhast"), MobMeta.MONSTER, false),
        ZOMBIE_PIGMAN(57, "zombie_pigman", getNMSClass("EntityPigZombie"), MobMeta.MONSTER, false),
        ENDERMAN(58, "enderman", getNMSClass("EntityEnderman"), MobMeta.MONSTER, false),
        CAVE_SPIDER(59, "cave_spider", getNMSClass("EntityCaveSpider"), MobMeta.MONSTER, false),
        SILVERFISH(60, "silverfish", getNMSClass("EntitySilverfish"), MobMeta.MONSTER, false),
        BLAZE(61, "blaze", getNMSClass("EntityBlaze"), MobMeta.MONSTER, false),
        MAGMACUBE(62, "magma_cube", getNMSClass("EntityMagmaCube"), MobMeta.MONSTER, false),
        ENDER_DRAGON(63, "ender_dragon", getNMSClass("EntityEnderDragon"), MobMeta.MONSTER, false),
        WITHER(64, "wither", getNMSClass("EntityWither"), MobMeta.MONSTER, false),
        BAT(65, "bat", getNMSClass("EntityBat"), MobMeta.AMBIENT, false),
        WITCH(66, "witch", getNMSClass("EntityWitch"), MobMeta.MONSTER, false),
        ENDERMITE(67, "endermite", getNMSClass("EntityEndermite"), MobMeta.MONSTER, false),
        GUARDIAN(68, "guardian", getNMSClass("EntityGuardian"), MobMeta.MONSTER, false),
        SHULKER(69, "shulker", getNMSClass("EntityShulker"), MobMeta.MONSTER, false),
        PIG(90, "pig", getNMSClass("EntityPig"), MobMeta.CREATURE, false),
        SHEEP(91, "sheep", getNMSClass("EntitySheep"), MobMeta.CREATURE, false),
        COW(92, "cow", getNMSClass("EntityCow"), MobMeta.CREATURE, false),
        CHICKEN(93, "chicken", getNMSClass("EntityChicken"), MobMeta.CREATURE, false),
        SQUID(94, "squid", getNMSClass("EntitySquid"), MobMeta.WATER_CREATURE, false),
        WOLF(95, "wolf", getNMSClass("EntityWolf"), MobMeta.CREATURE, false),
        MOOSHROOM(96, "mooshroom", getNMSClass("EntityMushroomCow"), MobMeta.CREATURE, false),
        SNOWMAN(97, "snowman", getNMSClass("EntitySnowman"), MobMeta.CREATURE, false),
        OCELOT(98, "ocelot", getNMSClass("EntityOcelot"), MobMeta.CREATURE, false),
        IRON_GOLEM(99, "villager_golem", getNMSClass("EntityIronGolem"), MobMeta.CREATURE, false),
        HORSE(100, "horse", getNMSClass("EntityHorse"), MobMeta.CREATURE, false),
        RABBIT(101, "rabbit", getNMSClass("EntityRabbit"), MobMeta.CREATURE, false),
        POLARBEAR(102, "polar_bear", getNMSClass("EntityPolarBear"), MobMeta.CREATURE, false),
        LLAMA(103, "llama", getNMSClass("EntityLlama"), MobMeta.CREATURE, false),
        LLAMA_SPIT(104, "llama_spit", getNMSClass("EntityLlamaSpit"), MobMeta.UNDEFINED, true),
        PARROT(105, "parrot", getNMSClass("EntityParrot"), MobMeta.CREATURE, false),
        VILLAGER(120, "villager", getNMSClass("EntityVillager"), MobMeta.CREATURE, false),
        ENDER_CRYSTAL(200, "ender_crystal", getNMSClass("EntityEnderCrystal"), MobMeta.UNDEFINED, true);
 
        private final int id;
        private final String name;
        private final Class<?> clazz;
        private final MobMeta meta;
        private final boolean special;
 
        
        public static Class<?> getNMSClass(String s)
        {
        	return ReflectionUtils.getRefClass("{nms}." + s);
        }
        
        private Type(final int id, final String name, Class<?> nmsClazz, final MobMeta meta,
                final boolean special) {
            this.id = id;
            this.name = name;
            this.clazz = nmsClazz;
            this.meta = meta;
            this.special = special;
        }
 
        public MobMeta getMeta() {
            return meta;
        }
 
        public int getId() {
            return id;
        }
 
        public String getName() {
            return name;
        }
 
        public Class<?> getNMSClass() {
            return clazz;
        }
 
        public boolean isSpecial() {
            return special;
        }
    }
 
    public enum MobMeta {
        MONSTER(META_LIST_MONSTER),
        CREATURE(META_LIST_CREATURE),
        WATER_CREATURE(META_LIST_WATER_CREATURE),
        AMBIENT(META_LIST_AMBIENT),
        UNDEFINED(null);
 
        private final Field field;
 
        private MobMeta(final Field field) {
            this.field = field;
        }
 
        /**
         * @return the BiomeMeta list field of this entity.
         *         <p>
         *         <b>Undefined will not be accepted and returns null.</b>
         *         </p>
         */
        public Field getField() {
            return field;
        }
    }
 
    public class NBTTagType {
        public static final int COMPOUND = 10;
        public static final int LIST = 9;
        public static final int STRING = 8;
        public static final int LONG_ARRAY = 12;
        public static final int INT_ARRAY = 11;
        public static final int BYTE_ARRAY = 7;
        public static final int DOUBLE = 6;
        public static final int FLOAT = 5;
        public static final int LONG = 4;
        public static final int INT = 3;
        public static final int SHORT = 2;
        public static final int BYTE = 1;
        public static final int BOOLEAN = 1;
        public static final int END = 0;
    }
	public static Class<?> genericAttributes = ReflectionUtils.getRefClass("{nms}.GenericAttributes");

    public static enum Attributes {
    	

    	
        MAX_HEALTH("generic.maxHealth", getOFF(genericAttributes, "maxHealth")),
        MOVEMENT_SPEED("generic.movementSpeed", getOFF(genericAttributes, "MOVEMENT_SPEED")),
        ATTACK_DAMAGE("generic.attackDamage", getOFF(genericAttributes, "ATTACK_DAMAGE")),
        FOLLOW_RANGE("generic.followRange", getOFF(genericAttributes, "FOLLOW_RANGE")),
        LUCK("generic.luck", getOFF(genericAttributes, "j")),
        ARMOR("generic.armor", getOFF(genericAttributes, "h")),
        ARMOR_TOUGHNESS("generic.armorToughness", getOFF(genericAttributes, "i")),
        ATTACK_SPEED("generic.attackSpeed", getOFF(genericAttributes, "g")),
        KNOCKBACK_RESISTANCE("generic.knockbackResistance", getOFF(genericAttributes, "c"));
    	
        private final String name;
        private final Object attribute;
        
        public static Object getOFF(Class<?> clazz, String fieldName)
        {
        	Field f = null;
			try {
				f = clazz.getField(fieldName);
			} catch (NoSuchFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	try {
				return f.get(null);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return f;
        }
        
        private Attributes(String nmsName, Object nmsAttribute) {
            this.name = nmsName;
            this.attribute = nmsAttribute;
        }
 
        /**
         * Returns the NMS name of the attribute. For example, <code>MAX_HEALTH</code> returns <code>"generic.maxHealth"</code>, and so on and so
         * forth.
         *
         * @return The name as a String.
         */
        public String getName() {
            return name;
        }
 
        /**
         * @return the IAttribute value of this type, used in place of <code>GenericAttributes.h</code> (for Attributes.ARMOR as an example).
         */
        public Object asIAttribute() {
            return attribute;
        }
    }
 
    
 
    static {
        final Class<?> clazz = ReflectionUtils.getRefClass("{nms}.BiomeBase");
        Field monster = null;
        Field creature = null;
        Field water = null;
        Field ambient = null;
        try {
            // These fields may vary depending on your version.
            // The new names can be found under
            // net.minecraft.server.<version>.BiomeBase
            monster = clazz.getDeclaredField("t");
            creature = clazz.getDeclaredField("u");
            water = clazz.getDeclaredField("v");
            ambient = clazz.getDeclaredField("w");
        } catch (final Exception e) {
            Bukkit.getLogger().warning("Wrong server version / software; BiomeMeta fields not found, aborting.");
        }
        META_LIST_MONSTER = monster;
        META_LIST_CREATURE = creature;
        META_LIST_WATER_CREATURE = water;
        META_LIST_AMBIENT = ambient;
        Class<?> csClass = ReflectionUtils.getRefClass("{cb}.CraftServer");
        CRAFTBUKKIT_SERVER = csClass.cast(Bukkit.getServer());
        MINECRAFT_SERVER = noTryCatch();
			
		
        
    }
    public static Object noTryCatch()
    {
    	try {
			return CRAFTBUKKIT_SERVER.getClass().getMethod("getServer").invoke(CRAFTBUKKIT_SERVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
    }
}
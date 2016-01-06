package me.tyler.terraria;

import me.tyler.terraria.packets.*;

public enum PacketType {

	CONNECTION_REQUEST(1, TerrariaPacketConnectionRequest.class),
	DISCONNECT(2, TerrariaPacketDisconnect.class),
	CONTINUE(3, TerrariaPacketContinue.class),
	PLAYER_INFO(4, TerrariaPacketPlayerInfo.class),
	INVENTORY_SLOT(5, TerrariaPacketInventorySlot.class),
	WORLD_INFO(7, TerrariaPacketWorldInfo.class),
	GET_SECTION(8, TerrariaPacketGetSection.class),
	STATUS(9, TerrariaPacketStatus.class),
	CONTINUE2(6, TerrariaPacketContinue.class),
	/*SEND_SECTION(10, TerrariaPacketSendSection.class),*/
	SPAWN_PLAYER(12, TerrariaPacketSpawnPlayer.class),
	UPDATE_PLAYER(13, TerrariaPacketUpdatePlayer.class),
	PLAYER_HP(16, TerrariaPacketPlayerHp.class),
	MODIFY_TILE(17, TerrariaPacketModifyTile.class),
	SEND_TILE_SQUARE(20, TerrariaPacketSendTileSquare.class),
	UPDATE_ITEM_DROP(21, TerrariaPacketUpdateItemDrop.class),
	UPDATE_ITEM_OWNER(22, TerrariaPacketUpdateItemOwner.class),
	NPC_UPDATE(23, TerrariaPacketUpdateNpc.class),
	CHAT_MESSAGE(25, TerrariaPacketChatMessage.class),
	PROJECTILE_UPDATE(27, TerrariaPacketProjectileUpdate.class),
	STRIKE_NPC(28, TerrariaPacketStrikeNpc.class),
	DESTROY_PROJECTILE(29, TerrariaPacketDestroyProjectile.class),
	TOGGLE_PVP(30, TerrariaPacketTogglePvp.class),
	ANIMATION(41, TerrariaPacketItemAnimation.class),
	MANA(42, TerrariaPacketMana.class),
	MANA_EFFECT(43, TerrariaPacketManaEffect.class),
	KILL_ME(44, TerrariaPacketKillMe.class),
	PLAYER_TEAM(45, TerrariaPacketPlayerTeam.class),
	SET_LIQUID(48, TerrariaPacketSetLiquid.class),
	UPDATE_BUFF(50, TerrariaPacketUpdatePlayerBuff.class),
	/*SPECIAL_NPC_EFFECT(51, TerrariaPacketSpecialNpcEffect.class),*/
	ADD_BUFF(55, TerrariaPacketAddBuff.class),
	SPAWN_BOSS(61, TerrariaPacketSpawnBoss.class),
	TELEPORT(65, TerrariaPacketTeleport.class),
	HEAL_OTHER(66, TerrariaPacketHealOther.class),
	CLIENT_UUID(68, TerrariaPacketUUID.class),
	RELEASE_NPC(71, TerrariaPacketReleaseNpc.class),
	COMBAT_TEXT(81, TerrariaPacketCombatText.class),
	PLACE_FRAME(89, TerrariaPacketPlaceFrame.class),
	EMOTE_BUBBLE(91, TerrariaPacketEmoteBubble.class),
	PORTAL_TELEPORT(96, TerrariaPacketPortalTeleport.class),
	NPC_PORTAL_TELEPORT(100, TerrariaPacketNpcPortalTeleport.class),
	NPC_SHOP_ITEM(104, TerrariaPacketNpcShopItem.class),
	OTHER(-1, TerrariaPacket.class)//Used to handle packets that the packet editor doesnt specifically care about
	;
	
	private final byte id;
	private final Class<? extends TerrariaPacket> packetClass;
	
	private <T extends TerrariaPacket> PacketType(int id, Class<? extends T> packetClass) {
		this.id = (byte) id;
		this.packetClass = packetClass;
	}
	
	public byte getId() {
		return id;
	}
	
	public <T> T getPacket(byte[] payload){
		return getPacket(id, payload);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getPacket(byte type, byte[] payload){
		try {
			return (T) packetClass.getConstructor(byte.class, byte[].class).newInstance(type, payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static PacketType getTypeFromId(byte id){
		for(PacketType type : values()){
			if(type.id == id){
				return type;
			}
		}
		
		return OTHER;
	}
	
}
